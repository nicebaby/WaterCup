package com.zslin.web.controller.admin;

import com.zslin.web.model.Account;
import com.zslin.web.service.IAccountService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Ji on 2017/3/24.
 * 查找用户余额
 */
@RestController
@RequestMapping("/userName")
public class DeviceController {
    @Autowired
    private IAccountService accountService;
    private Map data;
    @RequestMapping(value ="/remain",method= RequestMethod.GET)
    public JSONObject getremain(@RequestParam String username){
        Account user=accountService.findByEmail(username);
        String Paystate;
        String Paymoney;
        data=new LinkedHashMap();
        if(user.getPaystate()==null){
            Paystate="2";
        }else{
            Paystate=user.getPaystate();
        }
        if(user.getPaymoney()==null){
            Paymoney="0";
        }else{
            Paymoney=user.getPaymoney();
        }
        data.put("remain",user.getRemain());
        data.put("paystate",Paystate);
        data.put("paymoney",Paymoney);
        JSONObject result = JSONObject.fromObject(data);
        //清除状态跟充值金额
        user.setPaystate("2");
        user.setPaymoney("0");
        accountService.save(user);
        return result;
    }

}
