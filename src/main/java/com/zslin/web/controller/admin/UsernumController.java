package com.zslin.web.controller.admin;

import com.zslin.web.model.Account;
import com.zslin.web.service.IAccountService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/13.
 */
@RestController
@RequestMapping("/usernum")
public class UsernumController {
    @Autowired
    private IAccountService accountService;
    private Map data;
    @RequestMapping(method= RequestMethod.GET)
public JSONObject getdata(int draw, int start, int length){
    List<Account> test=accountService.findAll();
    data=new LinkedHashMap();
    int len=test.size();
    JSONArray test1 = JSONArray.fromObject(test);
    data.put("draw",draw);
    data.put("recordsTotal",len);
    data.put("recordsFiltered",len);
    if(start+length<len)
    { data.put("data",test1.subList(start,start+length));}
    else{
        data.put("data",test1.subList(start,len));
    }
    JSONObject result1 = JSONObject.fromObject(data);
    return result1;
  }
}
