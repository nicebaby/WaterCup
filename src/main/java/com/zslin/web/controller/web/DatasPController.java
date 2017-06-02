package com.zslin.web.controller.web;



import com.zslin.web.model.Senddata;
import com.zslin.web.service.ISenddataService;


import com.zslin.web.service.ISenddatasService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ji on 2017/4/21.
 */

@RestController
@RequestMapping("/device")
public class DatasPController {
    @Autowired
    ISenddatasService iSenddatasService;
    @RequestMapping(value = "/datasp",method = RequestMethod.POST)
    public JSONObject handledatas(@RequestBody JSONObject jobj){

        String id1=jobj.getString("id");
        String id2;
        if(id1.equals("1")){
            id2="1";
        }else{
            id2="3";
        }
        String datas=jobj.getString("data");
        Map<String,String> tmp=new HashMap<>();
        JSONObject jbt=JSONObject.fromObject(datas);
       /* List<String> data=new LinkedList<>();
        data.add(jbt.getString("temp"));
        data.add(jbt.getString("flow1"));
        data.add(jbt.getString("flow2"));
        data.add(jbt.getString("tds1"));
        data.add(jbt.getString("tds2"));*/
       Senddata sd=new Senddata();
       sd.setD_id(Integer.valueOf(id2));
       sd.setCstds(jbt.getString("tds2"));
       sd.setJstds(jbt.getString("tds1"));
       sd.setSuwd(jbt.getString("temp"));
       sd.setJsll(jbt.getString("flow1"));
       sd.setCsll(jbt.getString("flow2"));
       sd.setPh("OK");
       sd.setYl("OK");
        sd.setYlv("OK");
        sd.setZd("OK");
        sd.setRjy("OK");
        sd.setYcy("OK");
        sd.setSuyl("OK");
        DateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");

        format2.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        sd.setSentem( format2.format(new Date()).toString());

       this.iSenddatasService.save(sd);
        //成功
        if(datas.length()!=0){
            tmp.put("msg","Success");
            tmp.put("error_code","0");
            JSONObject response=JSONObject.fromObject(tmp);
            return response;
        }
        //失败
        else{
            tmp.put("msg","Error");
            tmp.put("error_code","1");
            JSONObject response=JSONObject.fromObject(tmp);
            return response;
        }
    }
}
