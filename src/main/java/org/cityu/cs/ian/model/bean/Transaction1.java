package org.cityu.cs.ian.model.bean;


import java.util.List;

public class Transaction1 {
    private String transactionId;
    private String from;
    private String to;
    private String fromPubkey;
    private String toPubkey;
    private List<Data> data;
    private String include;
    private String total;
    private String link;
    private String signatures;
    private String isSign;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getSignatures() {
        return signatures;
    }

    public void setSignatures(String signatures) {
        this.signatures = signatures;
    }

    public String getIsSign() {
        return isSign;
    }

    public void setIsSign(String isSign) {
        this.isSign = isSign;
    }

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFromPubkey() {
        return fromPubkey;
    }

    public void setFromPubkey(String fromPubkey) {
        this.fromPubkey = fromPubkey;
    }

    public String getToPubkey() {
        return toPubkey;
    }

    public void setToPubkey(String toPubkey) {
        this.toPubkey = toPubkey;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static class Data {
        private String item;
        private String price;

        @Override
        public String toString() {
            return "{" +
                    "item:'" + item + '\'' +
                    ", price:'" + price + '\'' +
                    '}';
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
    @Override
    public String toString() {
        return "params{" +
                "transactionId='" + transactionId + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", fromPubkey='" + fromPubkey + '\'' +
                ", toPubkey='" + toPubkey + '\'' +
                ", data=" + data +
                ", include='" + include + '\'' +
                ", total='" + total + '\'' +
                ", link='" + link + '\'' +
                ", signatures='" + signatures + '\'' +
                ", isSign='" + isSign + '\'' +
                '}';
    }

//    public String signJson(Transaction1 p){
//        JSONObject json = new JSONObject();
//        json.put("transactionId",p.getTransactionId());
//        json.put("from",p.getFrom());
//        json.put("to",p.getTo());
//        json.put("fromPubkey",p.getFromPubkey());
//        json.put("toPubkey",p.getToPubkey());
//        json.put("data",p.getData());
//        json.put("include",p.getInclude());
//        json.put("total",p.getTotal());
//        json.put("link",p.getLink());
//        return json.toJSONString();
//    }


}