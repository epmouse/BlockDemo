package org.cityu.cs.ian.service.Threads.impl;

import org.cityu.cs.ian.model.bean.Transaction1;
import org.cityu.cs.ian.service.Threads.ITransactionService;
import org.cityu.cs.ian.util.TransactonListOperatorUtils;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Override
    public boolean verifySign(Transaction1 json) {
        //todo-验证逻辑待实现
        return true;
    }
    //保存transaction到一个list集合中，最终等区块计算完（计算完才有当前区块的完整json串）吧list转成json传追加到当前计算的区块中（添加到区块的json中）

    @Override
    public void saveTransactionToList(Transaction1 json) {
        TransactonListOperatorUtils.addTransaction(json);
    }

}
