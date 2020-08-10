package com.dsys.base.util;

import com.dsys.api.bean.base.DictInfo;
import com.dsys.api.bean.base.TreeBean;
import com.dsys.api.common.enums.DoFlagEnum;
import com.dsys.common.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: DictTest
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/5/21 10:43
 */
public class DictUtil{
    
    
    public static void main (String[] args){
        addCountry();
    }
    
    
    public static List<DictInfo> addCountry (){
        
        Map<String, List<String>> maps1 = new HashMap<>();
        Map<String, String> maps2 = new HashMap<>();
        
        List<String> list1 = null;
        
        File file = new File("D:\\read.txt");
        try{
            /**构造一个BufferedReader类来读取文件*/
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            String[] args = null;
            /**使用readLine方法，一次读一行*/
            while((s = br.readLine()) != null){
                args = s.split(">");
                maps2.put(args[2],args[1]);
                if(!ToolUtil.isNullOrEmpty(maps1)){
                    list1 = maps1.get(args[2]);
                    if(ToolUtil.isNullOrEmpty(list1)){
                        list1 = new ArrayList<String>();
                    }
                }else{
                    list1 = new ArrayList<String>();
                }
                list1.add(args[3]);
                maps1.put(args[2],list1);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        Map<String, List<String>> maps = new HashMap<>();
        List<String> list = null;
        String s1 = "";
        for(Map.Entry<String, String> entry : maps2.entrySet()){
            s1 = maps2.get(entry.getKey());
            if(!ToolUtil.isNullOrEmpty(maps)){
                list = maps.get(s1);
                if(ToolUtil.isNullOrEmpty(list)){
                    list = new ArrayList<>();
                }
            }else{
                list = new ArrayList<>();
            }
            list.add(entry.getKey());
            maps.put(s1,list);
        }
        String gj = "0001";
        long sfcount = 4;
        long dqcount = 1;
        long cmcount = 1;
        List<String> cmlist = new ArrayList<>();
        List<String> dqList = new ArrayList<>();
        List<DictInfo> diList = new ArrayList<>();
        DictInfo di = null;
        /**map<省份，List<地级市>></>*/
        for(Map.Entry<String, List<String>> entry : maps.entrySet()){
            dqcount = 1;
            dqList = maps.get(entry.getKey());
            di = new DictInfo();
            di.setDictCode(gj + StringUtils.dfCode(sfcount));
            di.setDictName(entry.getKey());
            di.setParentCode(gj);
            di.setDoFlag(DoFlagEnum.ENABLED);
            di.setNote("中国" + entry.getKey());
            di.setIdentify("1");
            di.setOrderCode((int)sfcount);
            di.setCreateUser(Long.parseLong("89754731904950272"));
            diList.add(di);
            /**地区*/
            for(String s : dqList){
                di = new DictInfo();
                di.setDictCode(gj + StringUtils.dfCode(sfcount) + StringUtils.dfCode(dqcount));
                di.setDictName(s);
                di.setParentCode(gj + StringUtils.dfCode(sfcount));
                di.setDoFlag(DoFlagEnum.ENABLED);
                di.setNote("中国" + entry.getKey() + s);
                di.setIdentify("1");
                di.setOrderCode((int)dqcount);
                di.setCreateUser(Long.parseLong("89754731904950272"));
                diList.add(di);
                /** map<地级市，List<县级市></>**/
                cmlist = maps1.get(s);
                cmcount = 1;
                for(String ss : cmlist){
                    di = new DictInfo();
                    di.setDictCode(gj + StringUtils.dfCode(sfcount) + StringUtils.dfCode(dqcount) + StringUtils.dfCode(cmcount));
                    di.setDictName(ss);
                    di.setParentCode(gj + StringUtils.dfCode(sfcount) + StringUtils.dfCode(dqcount));
                    di.setDoFlag(DoFlagEnum.ENABLED);
                    di.setNote("中国" + entry.getKey() + s + ss);
                    di.setIdentify("1");
                    di.setOrderCode((int)cmcount);
                    di.setCreateUser(Long.parseLong("89754731904950272"));
                    diList.add(di);
                    cmcount++;
                }
                dqcount++;
            }
            sfcount++;
        }
        return diList;
    }
    
    
    public static List<TreeBean> map2list (String... arg){
        List<TreeBean> treeBeans = new ArrayList<>();
        
        return treeBeans;
    }
    
}
