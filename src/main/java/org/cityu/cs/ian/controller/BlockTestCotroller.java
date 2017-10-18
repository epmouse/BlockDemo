package org.cityu.cs.ian.controller;

import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.model.bean.Transaction1;
import org.cityu.cs.ian.service.Threads.IBlockAcceptService;
import org.cityu.cs.ian.service.Threads.ITransactionService;
import org.cityu.cs.ian.util.Constant;
import org.cityu.cs.ian.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    @RequestMapping(value = "transaction", method = RequestMethod.POST)
    public String transactionAccept(@RequestBody Transaction1 transaction1) {
        if (transactionService.verifySign(transaction1)) {
            transactionService.saveTransactionToList(transaction1);
            return Constant.SUCCESS_RESPONSE;
        }
        return Constant.ERR_RESPONSE;
    }

    @RequestMapping(value = "block", method = RequestMethod.POST)
    public Map<String, String> acceptBlock(@RequestBody BlockBean block) {
        if (blockAcceptService.verifyBlock(block)) {
            blockAcceptService.interruptPow();
            boolean b = blockAcceptService.saveBlock(block);
            if (b) {
                return getResponseMap(true, null);
            } else {
                System.out.println("BlockAcceptServicee中saveBlock（）方法文件写入失败，请查看异常");

                return getResponseMap(false,"文件写入失败");
            }
        } else {
            return getResponseMap(false,"block验证失败");
        }
    }

    private Map<String, String> getResponseMap(boolean b, String errDetails) {
        Map<String, String> backMap = new HashMap<>();
        backMap.put("status", b ? "ok" : "err");
        backMap.put("details", errDetails == null ? "" : errDetails);
        backMap.put("url", PropertiesUtil.readValue("config.properties", "currentServerUrl"));
        return backMap;
    }

//    1、同步block  下载没有的blcok文件。（1提供下载接口， 2下载并保存）
//    2、给客户端提供所有block的json串。（考虑分页）从后往前，先发最新的
//    3、根据transactionid查询这条transactiong的信息 返回 json
//    4、根据blockheight 查询出对应block  返回json


}

