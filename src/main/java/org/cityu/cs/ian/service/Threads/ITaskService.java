package org.cityu.cs.ian.service.Threads;

public interface ITaskService {
    /**
     * 接收客户服务器发来的transation
     * @return
     */
    String acceptTransations();

    /**
     * 挖矿，并广播出去
     */
    void powCalculate();

}
