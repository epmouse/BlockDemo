package org.cityu.cs.ian.controller;

import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.model.bean.Transaction1;
import org.cityu.cs.ian.service.Threads.IBlockAcceptService;
import org.cityu.cs.ian.service.Threads.ITransactionService;
import org.cityu.cs.ian.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("block")
public class BlockTestCotroller  {

    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IBlockAcceptService blockAcceptService;


    @RequestMapping(value = "transaction", method = RequestMethod.POST)
    public String transactionAccept(@RequestBody Transaction1 transaction1) {
       if(transactionService.verifySign(transaction1)){
           transactionService.saveTransactionToList(transaction1);
           return Constant.SUCCESS_RESPONSE;
       }
       return Constant.ERR_RESPONSE;
    }

    @RequestMapping(value = "block", method = RequestMethod.POST)
    public void acceptBlock(@RequestBody BlockBean block){



//        /1、验证区块（以下为验证顺序）
//        验证blockhash
//        验证previoushash
//        验证mercalroot
//        验证成功后放入链。（存成文件放入文件夹）
//        中断正在计算的线程，并清除当前数据。
//        重启计算线程（新的计算）
    }

//    1、同步block  下载没有的blcok文件。（1提供下载接口， 2下载并保存）
//    2、给客户端提供所有block的json串。（考虑分页）从后往前，先发最新的
//    3、根据transactionid查询这条transactiong的信息 返回 json
//    4、根据blockheight 查询出对应block  返回json


}

