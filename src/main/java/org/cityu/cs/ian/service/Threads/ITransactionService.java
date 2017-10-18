package org.cityu.cs.ian.service.Threads;

import org.cityu.cs.ian.model.bean.Transaction1;

public interface ITransactionService {

    boolean verifySign(Transaction1 json);

    void saveTransactionToList(Transaction1 json);

}
