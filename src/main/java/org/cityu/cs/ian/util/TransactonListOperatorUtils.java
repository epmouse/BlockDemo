package org.cityu.cs.ian.util;

import org.cityu.cs.ian.model.bean.Transaction1;

import java.util.ArrayList;
import java.util.List;

public class TransactonListOperatorUtils {

    public final static List<Transaction1> transactionList = new ArrayList<>();

    /**
     * 获取到整个计算时间内接收的所有transaction数据
     * 调用此方法会清空原来的transaction 集合，请只在计算完区块，往里面添加数据的时候再使用。
     * @return
     */
    public synchronized static List<Transaction1> getTransactionList() {
        //每次返回一个新集合，然后清空transactionlist  为了保证此过程中 没有新的add数据进来，吧这几个步骤放入同步方法中
        List<Transaction1> list=new ArrayList<>();
        list.addAll(transactionList);
        transactionList.clear();
        return list;
    }

    /**
     * 把新接收的transaction 添加到缓存集合中
     * @param transaction
     * @return
     */
    public synchronized static boolean addTransaction(Transaction1 transaction) {
        return transactionList.add(transaction);
    }
}
