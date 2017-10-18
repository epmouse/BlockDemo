package org.cityu.cs.ian.model.bean;

import org.cityu.cs.ian.model.bean.Transaction1;

import java.util.List;

public class BlockBean {


    /**
     * transacionCount : fffgfg
     * blockHeight : 区块高度 第几个区块
     * blockHeader : {"previousHash":"","merkleRoot":" 标准算法","timeStep":"算完block的时间","randemTime":"计算开始时间（计算用种子）","nonce":"最后一次添加的数字字符串 （计算用种子）","blockHash":"最终算出来的hash值","nextHash":"下一个区块hash"}
     * transacions : [{"transaction":{"transaction_id":"asdf","from":"someonesid","to":"anothersid","data":[{"item":"xxx","price":"123123"},{"item":"xxx","price":"123123"}],"include":"some_transactionsid","total":"xxxx","link":"www.xcvasdfsadfsadf"},"signatures":{"from_signature":"Asign","to_signature":"Bsign"},"is_sign":true}]
     */

    private int transactionCount;
    private long blockHeight;
    private BlockHeaderBean blockHeader;
    private List<Transaction1> transaction1s;

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public long getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(long blockHeight) {
        this.blockHeight = blockHeight;
    }

    public BlockHeaderBean getBlockHeader() {
        return blockHeader;
    }

    public void setBlockHeader(BlockHeaderBean blockHeader) {
        this.blockHeader = blockHeader;
    }

    public List<Transaction1> getTransaction1s() {
        return transaction1s;
    }

    public void setTransaction1s(List<Transaction1> transaction1s) {
        this.transaction1s = transaction1s;
    }

    public static class BlockHeaderBean {
        /**
         * previousHash :
         * merkleRoot :  标准算法
         * timeStep : 算完block的时间
         * randemTime : 计算开始时间（计算用种子）
         * nonce : 最后一次添加的数字字符串 （计算用种子）
         * blockHash : 最终算出来的hash值
         * nextHash : 下一个区块hash
         */

        private String previousHash;
        private String merkleRoot;
        private long timeStamp;
        private long randomTime;
        private String nonce;
        private String blockHash;
        private String nextHash;

        public String getPreviousHash() {
            return previousHash;
        }

        public void setPreviousHash(String previousHash) {
            this.previousHash = previousHash;
        }

        public String getMerkleRoot() {
            return merkleRoot;
        }

        public void setMerkleRoot(String merkleRoot) {
            this.merkleRoot = merkleRoot;
        }

        public long getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
        }

        public long getRandomTime() {
            return randomTime;
        }

        public void setRandomTime(long randomTime) {
            this.randomTime = randomTime;
        }

        public String getNonce() {
            return nonce;
        }

        public void setNonce(String nonce) {
            this.nonce = nonce;
        }

        public String getBlockHash() {
            return blockHash;
        }

        public void setBlockHash(String blockHash) {
            this.blockHash = blockHash;
        }

        public String getNextHash() {
            return nextHash;
        }

        public void setNextHash(String nextHash) {
            this.nextHash = nextHash;
        }
    }


}
