package org.cityu.cs.ian.service.Threads.impl;

import org.cityu.cs.ian.model.IBlockModel;
import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.model.bean.Transaction1;
import org.cityu.cs.ian.service.Threads.IBlockAcceptService;
import org.cityu.cs.ian.util.JsonUtil;
import org.cityu.cs.ian.util.SHA256;
import org.cityu.cs.ian.util.merkle.MerkleTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockAcceptService implements IBlockAcceptService {
    @Autowired
    private IBlockModel blockModel;

    @Override
    public boolean verifyBlock(BlockBean blockBean) {
        //      /1、验证区块（以下为验证顺序）
        BlockBean.BlockHeaderBean blockHeader = blockBean.getBlockHeader();
        boolean b = verifyBlockhash(blockHeader);
        boolean b1 = verifyPreviousHash(blockHeader);
        boolean b2 = verifyMercalroot(blockBean);
        return b&&b1&&b2;
    }

    //todo-验证mercalroot 缺少mercleroot的计算
    private boolean verifyMercalroot(BlockBean blockbean) {
        String merkleRoot = blockbean.getBlockHeader().getMerkleRoot();
        List<Transaction1> transaction1s = blockbean.getTransaction1s();
        ArrayList<String> transactionJsonList = new ArrayList<>();
        for(Transaction1 transaction1:transaction1s){
            transactionJsonList.add(JsonUtil.toJson(transaction1));
        }
        String root = MerkleTreeUtil.getRoot(transactionJsonList);
        return root.equals(merkleRoot);
    }

    //验证previoushash
    private boolean verifyPreviousHash(BlockBean.BlockHeaderBean blockHeader) {
        String topBlockHash = blockModel.getTopBlockHash();
        String previousHash = blockHeader.getPreviousHash();
        return previousHash.equals(topBlockHash);
    }

    // 验证blockhash
    private boolean verifyBlockhash(BlockBean.BlockHeaderBean blockHeader) {
        String hashBaseStr = TaskService.BASENACL + blockHeader.getRandomTime() + blockHeader.getNonce();
        String sha256StrJava = SHA256.getSHA256StrJava(hashBaseStr);
        String blockHash = blockHeader.getBlockHash();
        return sha256StrJava.equals(blockHash);
    }

    @Override
    public void interruptPow() {
        TaskService.isInterrupt=true;
    }

    @Override
    public boolean saveBlock(BlockBean blockBean) {
        return blockModel.saveBlockToLocal(JsonUtil.toJson(blockBean));

    }
}
