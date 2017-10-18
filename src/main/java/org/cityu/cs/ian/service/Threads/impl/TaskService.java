package org.cityu.cs.ian.service.Threads.impl;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.commons.io.FileUtils;
import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.model.bean.Transaction1;
import org.cityu.cs.ian.service.Threads.ITaskService;
import org.cityu.cs.ian.util.*;
import org.cityu.cs.ian.util.PropertiesUtil;
import org.cityu.cs.ian.util.merkle.MerkleTreeUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements ITaskService {
    private volatile static boolean isInterrupt;
    public final static String BASENACL = "This is first block";
    @Override
    public String acceptTransations() {
        return null;
    }

    @Override
    @Async
    public void powCalculate() {
        final long naclTime = System.currentTimeMillis();//当前时间计算出来放入json，验证用
        int i = 0;
        calculate(naclTime, i);
    }
    private void calculate(long startTime, int i) {
        String currentStr = BASENACL + startTime + (++i);
        if (!isInterrupt) {//接收block 先停止计算线程，验证失败继续计算，验证成功 直接销毁线程进行下一次计算
            String hash = SHA256.getSHA256StrJava(currentStr);
            if ("00000000".equals(hash.substring(0, 7))) {
                saveAndPostBlock(i, startTime, hash, System.currentTimeMillis());
            } else {
                calculate(startTime, i);
            }
        }
    }

    /**
     * 把计算完的区块添加到总链，并post出去
     */
    private void saveAndPostBlock(int lastI, long startTime, String lastHash, long endTime) {
        String blockJson = assemblyBlock(lastI, startTime, lastHash, endTime);
        saveBlockToLocal(blockJson);
        postBlock(blockJson);
        powCalculate();//新区块计算开始
    }

    /**
     * 把区块存成文件保存到本地
     * @param blockJson
     */
    public void saveBlockToLocal(String blockJson) {
        String rootPath = getBlockPath();
        File file = new File(rootPath + "/" + getCurrentBlockName());
        try {
            FileUtils.write(file,blockJson,"utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getBlockPath() {
        return PropertiesUtil.readValue("config.properties","block.localPath");
    }

    /**
     * 获取新区块文件名称  暂定使用区块高度命名
     * @return
     */
    private int getCurrentBlockName() {
        return getTotalBlockCount() + 1;
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
                }
                @Override
                public void onResponse(Response response) throws IOException {
                    //多server发送，暂时不考虑返回值
                }
            });
        }
    }

    /**
     * 拼装区块
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
        blockBean.setBlockHeight(getCurrentBlockName());
        blockBean.setBlockHeader(getBlockHeader(transactionList, startTime, endTime, lastI, lastHash));
        blockBean.setTransaction1s(transactionList);
        return JsonUtil.toJson(blockBean);
    }

    /**
     * 组装blockhearder
     * @return
     */
    private BlockBean.BlockHeaderBean  getBlockHeader(List<Transaction1> transactionList, long startTime, long endTime, int lastI, String lastHash) {
        BlockBean.BlockHeaderBean blockHeaderBean = new BlockBean.BlockHeaderBean();
        blockHeaderBean.setPreviousHash(getTopBlockHash());
        ArrayList<String> transactionJsonList = new ArrayList<>();
        for(Transaction1 transaction1:transactionList){
            transactionJsonList.add(JsonUtil.toJson(transaction1));
        }
        blockHeaderBean.setMerkleRoot(MerkleTreeUtil.getRoot(transactionJsonList));
        blockHeaderBean.setTimeStamp(endTime);
        blockHeaderBean.setRandomTime(startTime);
        blockHeaderBean.setNonce(lastI + "");
        blockHeaderBean.setBlockHash(lastHash);
        return blockHeaderBean;
    }

    /**
     * 获取当前链的最后一个区块的hash
     *
     * @return
     */
    private String getTopBlockHash() {
        File file=null;
        List<File> files = getAllBolckFiles();
        if(files!=null&files.size()>0){
           file = files.get(files.size() - 1);
        }
        try {
            String json = FileUtils.readFileToString(file, "utf-8");
            BlockBean blockBean = JsonUtil.fromJson(json, BlockBean.class);
            return blockBean.getBlockHeader().getBlockHash();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前链的高度
     *
     * @return
     */
    public int getTotalBlockCount() {
        List<File> files = getAllBolckFiles();
        if(files!=null&files.size()>0)
        System.out.println(files.get(files.size()-1));
        return files.size();
    }

    /**
     * 获取所有区块文件
     * @return
     */
    public List<File> getAllBolckFiles() {
        return (List<File>) FileUtils.listFiles(new File(getBlockPath()),null,true);
    }
}
