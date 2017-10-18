package org.cityu.cs.ian.service.Threads.impl;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.cityu.cs.ian.model.IBlockModel;
import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.model.bean.Transaction1;
import org.cityu.cs.ian.service.Threads.ITaskService;
import org.cityu.cs.ian.util.*;
import org.cityu.cs.ian.util.PropertiesUtil;
import org.cityu.cs.ian.util.merkle.MerkleTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements ITaskService {
    @Autowired
    private IBlockModel blockModel;

    public volatile static boolean isInterrupt;
    public final static String BASENACL = "This is first block";

    @Override
    @Async
    public void powCalculate() {
        final long naclTime = System.currentTimeMillis();//当前时间计算出来放入json，验证用
        int i = 0;
        calculateByFor(BASENACL, i, naclTime);
    }

    public void calculateByFor(String hash, int i, long naclTime) {
        while (!"0000000".equals(hash.substring(0, 7)) && !isInterrupt) {
            i++;
            hash = SHA256.getSHA256StrJava(TaskService.BASENACL + naclTime + i);
        }
        if (!isInterrupt) {
            saveAndPostBlock(i, naclTime, hash, System.currentTimeMillis());
        } else {
            TransactonListOperatorUtils.getTransactionList();//清空transaction
            powCalculate();//重新开始计算
        }
    }


    /**
     * 把计算完的区块添加到总链，并post出去
     */
    private void saveAndPostBlock(int lastI, long startTime, String lastHash, long endTime) {
        String blockJson = assemblyBlock(lastI, startTime, lastHash, endTime);
        boolean b = blockModel.saveBlockToLocal(blockJson);
        if (b) {
            postBlock(blockJson);
            powCalculate();//新区块计算开始
        }else {
            System.out.println("TaskService中saveAndPostBlock（）方法文件写入失败，请查看异常");
        }
    }

    /**
     * 广播区块
     *
     * @param blockJson
     */
    public void postBlock(String blockJson) {
        String s = PropertiesUtil.readValue("config.properties", "block.urls");
        String[] split = s.split(";");
        for (int i = 0; i < split.length; i++) {
            //考虑到同时广播给所有server，采用异步处理
            HttpUtils.getInstance().postJsonByAsync(split[i], blockJson, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    //todo-多server发送，统计失败的server
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    //todo-多server发送，统计成功的server
                }
            });
        }
    }

    /**
     * 拼装区块
     *
     * @param lastI
     * @param startTime
     * @param lastHash
     * @param endTime
     * @return
     */
    private String assemblyBlock(int lastI, long startTime, String lastHash, long endTime) {
        BlockBean blockBean = new BlockBean();
        List<Transaction1> transactionList = TransactonListOperatorUtils.getTransactionList();
        blockBean.setTransactionCount(transactionList.size());
        blockBean.setBlockHeight(blockModel.getCurrentBlockName());
        blockBean.setBlockHeader(getBlockHeader(transactionList, startTime, endTime, lastI, lastHash));
        blockBean.setTransaction1s(transactionList);
        return JsonUtil.toJson(blockBean);
    }

    /**
     * 组装blockhearder
     *
     * @return
     */
    private BlockBean.BlockHeaderBean getBlockHeader(List<Transaction1> transactionList, long startTime, long endTime, int lastI, String lastHash) {
        BlockBean.BlockHeaderBean blockHeaderBean = new BlockBean.BlockHeaderBean();
        blockHeaderBean.setPreviousHash(blockModel.getTopBlockHash());
        ArrayList<String> transactionJsonList = new ArrayList<>();
        for (Transaction1 transaction1 : transactionList) {
            transactionJsonList.add(JsonUtil.toJson(transaction1));
        }
        blockHeaderBean.setMerkleRoot(MerkleTreeUtil.getRoot(transactionJsonList));
        blockHeaderBean.setTimeStamp(endTime);
        blockHeaderBean.setRandomTime(startTime);
        blockHeaderBean.setNonce(lastI + "");
        blockHeaderBean.setBlockHash(lastHash);
        return blockHeaderBean;
    }
}
