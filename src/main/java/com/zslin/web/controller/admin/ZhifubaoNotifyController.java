package com.zslin.web.controller.admin;


import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.zslin.web.model.Account;
import com.zslin.web.service.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 * Created by Administrator on 2017/5/19.
 */
@Controller
@RequestMapping("/zhifubao")
public class ZhifubaoNotifyController {
    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/back", method = RequestMethod.POST)
    public String zhifubao_notify(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        System.out.println("收到支付宝异步通知！");

        Map<String, String> params = new HashMap<String, String>();
        String uRemain; //用户当前余额
        //取出所有参数是为了验证签名
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            params.put(parameterName, request.getParameter(parameterName));
        }
        boolean signVerified;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, Configs.getAlipayPublicKey(), "UTF-8");
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "failed";
        }
        if (signVerified) {
            String outtradeno = params.get("out_trade_no");
            System.out.println(outtradeno + "号订单回调通知。");
            System.out.println("验证签名成功！");

            //若参数中的appid和填入的appid不相同，则为异常通知
            if (!Configs.getAppid().equals(params.get("app_id"))) {
                System.out.println("与付款时的appid不同，此为异常通知，应忽略！");
                return "failed";
            }

            //处理业务开始，读取回调信息
            String username = params.get("body");         //用户名
            String total_amount = (String)params.get("total_amount");  //金额


            System.out.println("username"+username);
            System.out.println("total_amount:"+total_amount);
            //------------------------------

            String status = params.get("trade_status");
            if (status.equals("TRADE_SUCCESS")) { //如果状态是成功
                //////////修改数据库中的数据////////////////
                Account user=accountService.findByNickname(username);
                if(user.getRemain()==null)
                {
                    uRemain=0+"";
                }else
                {
                    uRemain=user.getRemain();
                }
                System.out.println("remainbeform"+uRemain);
                float userRemain = Float.parseFloat(uRemain);
                float userPay = Float.parseFloat(total_amount);
                System.out.println("total_amount"+userPay);
                float remain = userRemain + userPay;
                String remainfinal=remain+"";
                user.setRemain(remainfinal);
                user.setPaymoney(userPay+"");
                user.setPaystate("1");
                accountService.save(user);
                System.out.println("remainafter"+user.getRemain());
                System.out.println("success");
            } else {
                //支付不成功，记录金额与状态
                Account user0=accountService.findByNickname(username);
                float userPay0 = Float.parseFloat(total_amount);
                user0.setPaymoney(userPay0+"");
                user0.setPaystate("0");
                accountService.save(user0);
                System.out.println("failed,information：" + status);
            }
        } else { //如果验证签名没有通过
            return "failed";
        }
        return "success";
    }
}