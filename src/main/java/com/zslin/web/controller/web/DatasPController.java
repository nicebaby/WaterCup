package com.zslin.web.controller.web;



import com.zslin.web.model.Alarm;
import com.zslin.web.model.Device;
import com.zslin.web.model.Maintain;
import com.zslin.web.model.Senddata;
import com.zslin.web.service.*;


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
    @Autowired
    IDeviceService iDeviceService;
    @Autowired
    IAlarmService iAlarmService;
    @Autowired
    IMaintainService iMaintainService;
    @RequestMapping(value = "/datasp",method = RequestMethod.POST)
    public JSONObject handledatas(@RequestBody JSONObject jobj){
        Alarm alarm=this.iAlarmService.findOne(1);
        String id1=jobj.getString("id");
        String id2;
        Integer target=0;
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
       Device device=this.iDeviceService.findOne(Integer.valueOf(id2));
       Maintain maintain=new Maintain();
       sd.setD_id(Integer.valueOf(id2));
       sd.setCstds(jbt.getString("tds2"));
        if (Float.parseFloat(jbt.getString("tds2"))<alarm.getCstdsdown()
                ||Float.parseFloat(jbt.getString("tds2"))>alarm.getCstdsup()){
           maintain.setCstds(Float.parseFloat(jbt.getString("tds2")));
           target=1;
       }
       sd.setJstds(jbt.getString("tds1"));
        if (Float.parseFloat(jbt.getString("tds1"))<alarm.getJstdsdown()
                ||Float.parseFloat(jbt.getString("tds1"))>alarm.getJstdsup()){
            maintain.setJstds(Float.parseFloat(jbt.getString("tds1")));
            target=1;
        }
       sd.setSuwd(jbt.getString("temp"));
        if (Float.parseFloat(jbt.getString("temp"))<alarm.getSuwddown()
                ||Float.parseFloat(jbt.getString("temp"))>alarm.getSuwdup()){
            maintain.setSuwd(Float.parseFloat(jbt.getString("temp")));
            target=1;
        }
       sd.setJsll(jbt.getString("flow1"));
        if (Float.parseFloat(jbt.getString("flow1"))<alarm.getJslldown()
                ||Float.parseFloat(jbt.getString("flow1"))>alarm.getJsllup()){
            maintain.setJsll(Float.parseFloat(jbt.getString("flow1")));
            target=1;
        }
       sd.setCsll(jbt.getString("flow2"));
        if (Float.parseFloat(jbt.getString("flow2"))<alarm.getCslldown()
                ||Float.parseFloat(jbt.getString("flow2"))>alarm.getCsllup()){
            maintain.setCsll(Float.parseFloat(jbt.getString("flow2")));
            target=1;
        }
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
       if (target==1){
           if (this.iMaintainService.findByDeviceId(Integer.valueOf(id2))!=null){
               this.iMaintainService.delete(this.iMaintainService.findByDeviceId(Integer.valueOf(id2)).getId());
           }
           maintain.setPh(null);
           maintain.setYl(null);
           maintain.setYlv(null);
           maintain.setZd(null);
           maintain.setRjy(null);
           maintain.setYcy(null);
           maintain.setSuyl(null);
           maintain.setSentem( format2.format(new Date()).toString());
           maintain.setDeviceId(Integer.valueOf(id2));
           maintain.setDevicename(device.getName());
           maintain.setSitename(device.getSite().getName());
           this.iMaintainService.save(maintain);
       }
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
