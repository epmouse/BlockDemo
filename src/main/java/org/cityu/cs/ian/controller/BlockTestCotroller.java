package org.cityu.cs.ian.controller;

import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.model.bean.Transaction1;
import org.cityu.cs.ian.service.Threads.IBlockAcceptService;
import org.cityu.cs.ian.service.Threads.ITransactionService;
import org.cityu.cs.ian.util.Constant;
import org.cityu.cs.ian.util.PropertiesUtil;
import org.cityu.cs.ian.util.ResponseMsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("block")
public class BlockTestCotroller {

    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IBlockAcceptService blockAcceptService;


    @RequestMapping(value = "add/transaction", method = RequestMethod.POST)
    public String transactionAccept(@RequestBody Transaction1 transaction1) {
        if (transactionService.verifySign(transaction1)) {
            transactionService.saveTransactionToList(transaction1);
            return Constant.SUCCESS_RESPONSE;
        }
        return Constant.ERR_RESPONSE;
    }

    @RequestMapping(value = "add/block", method = RequestMethod.POST)
    public Map<String, String> acceptBlock(@RequestBody BlockBean block) {
        if (blockAcceptService.verifyBlock(block)) {
            blockAcceptService.interruptPow();
            boolean b = blockAcceptService.saveBlock(block);
            if (b) {
                return ResponseMsgUtils.getResponseMap(true, null);
            } else {
                System.out.println("BlockAcceptServicee中saveBlock（）方法文件写入失败，请查看异常");

                return ResponseMsgUtils.getResponseMap(false,"文件写入失败");
            }
        } else {
            return ResponseMsgUtils.getResponseMap(false,"block验证失败");
        }
    }


    @RequestMapping(value = "syncBlock/{blockName}", method = RequestMethod.GET)
    public void syncBlocks(@PathVariable("blockName") String blockName) {
        //todo-文件的下载操作，找到对应名字的区块文件并以流的形式返回
    }
    @RequestMapping(value = "syncBlock/Highest", method = RequestMethod.GET)
    public String getTopBlockHeight(String blockName) {
        //todo-返回目前最高区块的高度，方便其他server根据目前的缺失情况，循环调用下载接口，下载区块

        return "";
    }
    @RequestMapping(value = "syncBlock/blockDetail", method = RequestMethod.GET)
    public String getBlockByHeight(String blockName) {
        //todo-根据blockheight 查询出对应block  返回json

        return "";
    }
    @RequestMapping(value = "syncBlock/getTransaction", method = RequestMethod.GET)
    public String getTransaction(String transactionid) {
        //todo-根据transactionid查询这条transactiong的信息 返回 json

        return "";
    }
    @RequestMapping(value = "syncBlock/all", method = RequestMethod.GET)
    public String getAllBlockForClient(String page) {  //1为最新的10条,依次往下
        //todo-给客户端提供所有block的json串。（考虑分页）从后往前，先发最新的
        return "";
    }

//todo-除以上contrller操作外 还需要做调用下载和获取最高区块的两个接口的操作



//    1、同步block  下载没有的blcok文件。（1提供下载接口， 2下载并保存）
//    2、给客户端提供所有block的json串。（考虑分页）从后往前，先发最新的
//    3、根据transactionid查询这条transactiong的信息 返回 json
//    4、根据blockheight 查询出对应block  返回json


}

