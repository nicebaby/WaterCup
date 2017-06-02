package com.zslin.web.controller.admin;


import com.zslin.web.model.Senddata;
import com.zslin.web.model.Site;
import com.zslin.web.service.ISenddataService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by Ji on 2017/3/24.
 */
@RestController
@RequestMapping("/datas")
public class DatasController {
    @Autowired
    private ISenddataService senddataService;
    private Map data;
    @RequestMapping(value="/{tableId}",method= RequestMethod.GET)
    public JSONObject getdatas(@PathVariable String tableId,String sid, String did,int draw,int start, int length){
        List<Senddata> test=senddataService.findByD_id(Integer.valueOf(did));
        data = new LinkedHashMap();
        int len=test.size();
       /* for(int i=0;i<len;i++){
            Senddata j=test.get(i);
            if(j.getSe_id()!=Integer.valueOf(sid)){
                test.remove(i);
                --len;
                --i;`
            }
        }*/
        Collections.sort(test);
        len=test.size();
        if(tableId.equals("table2")){
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
        return result1;}
        else if(tableId.equals("table1")){
            data.put("tt",test.get(0));
            JSONObject result2 = JSONObject.fromObject(data);
            return result2;
        }else
        {
            if(length>len)
            {
                for(Senddata plot1:test)
                {
                    data.put(plot1.getId()+"",plot1);
                }
                JSONObject result3 = JSONObject.fromObject(data);
                return result3;
            }else
                {
                    for(int j=0;j<length;j++){
                        data.put(j+"",test.get(j));
                    }
                    JSONObject result4 = JSONObject.fromObject(data);
                    return result4;
                }
        }
    }
}
