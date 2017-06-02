package com.zslin.web.controller.admin;

import com.sun.org.apache.xml.internal.utils.StringToIntTable;
import com.zslin.web.model.AjaxResponceBody;
import com.zslin.web.model.Device;
import com.zslin.web.service.IDeviceService;
import com.zslin.web.service.ISendsiteService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.zslin.web.model.Site;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/30.
 */

@RestController
@RequestMapping("/api")
public class SearchController {
    @Autowired
    private ISendsiteService sendsiteService;
    @Autowired
    private IDeviceService iDeviceService;
    private Map<String,Site> data;
    private Map<String,Device> data1;
    @RequestMapping(value = "/sites",method= RequestMethod.GET)
    public JSONObject getAllsites(String id,String user){
        System.out.println("s");
         if (id.equals("0")) {
             System.out.println("ss");
             List<Site> list = this.sendsiteService.findAll();
             System.out.println(list.get(1).getName());
             data = new HashMap<String, Site>();
             System.out.println("sss");
             for (Site list1 : list) {
                 System.out.println("shaha"+list1.getName());
                 System.out.println(list1.getId() + "");
                 list1.setUser(null);
                 data.put(list1.getId() + "", list1);

             }
             System.out.println("ssss");
             System.out.println("why"+data.get("1").getName());
             JSONObject test = JSONObject.fromObject(data);
             System.out.println("why???"+test.get("1").getClass().getName());
             return test;
         } else {
             List<Device> list = iDeviceService.findDeviceBySId(Integer.valueOf(id));
             data1 = new HashMap<String, Device>();
             for (Device list1 : list) {
                 data1.put(list1.getD_id() + "", list1);
             }
             JSONObject test = JSONObject.fromObject(data1);
             return test;
         }

    }
 }



