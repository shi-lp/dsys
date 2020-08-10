package com.dsys.common.util;

import com.dsys.api.bean.base.TreeBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: TreeUtil.java Description: 描述
 *
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @version 1.0
 * @created 2019年12月18日 下午3:37:08
 * @update 2019年12月18日 下午3:37:08
 */

public class TreeUtil{

    

    /**
     * Fastjson的SerializerFeature序列化属性
     * QuoteFieldNames———-输出key时是否使用双引号,默认为true
     * WriteMapNullValue——–是否输出值为null的字段,默认为false
     * WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
     * WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
     * WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
     * WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
     * @param list
     * @return
     */
    public static String createJsonStr (List<TreeBean> list) {
        String jsonarr = null;
        if (!ToolUtil.isNullOrEmpty(list)) {
            jsonarr = JsonUtils.list2json(new TreeUtil.TreeMethod().list2Tree(list));
        }
        return jsonarr;
    }
    
    public static List<TreeBean> result2List(List<TreeBean> list){
        return new TreeUtil.TreeMethod().list2Tree(list);
    }
    
    
    public static final class TreeMethod{
        
        Map <String, TreeBean> map = new HashMap <String, TreeBean>();
    
        Map <String, List <TreeBean>> returnMap = new HashMap <String, List <TreeBean>>();
        /**
         * @param list
         * @param oraderRole 0 升序 1 降序
         * @return
         * @discription list排序
         * @author shilp
         * @created 2019年12月18日 下午4:37:37
         * @update 2019年12月18日 下午4:37:37
         */
        public List <TreeBean> orderList (List<TreeBean> list,int oraderRole) {
            Collections.sort(list, new Comparator <TreeBean>() {
                @Override
                public int compare(TreeBean o1, TreeBean o2) {
                    if (oraderRole == 0) {
                        return o1.getOrderNum() - o2.getOrderNum();
                    }
                    return o2.getOrderNum() - o1.getOrderNum();
                }
            });
            return list;
        }
    
        /**
         * @param list
         * @return
         * @discription list转换为以父Id为主键的map
         * @author shilp
         * @created 2019年12月18日 下午3:57:08
         * @update 2019年12月18日 下午3:57:08
         */
        public Map <String, List <TreeBean>> list2Map (List<TreeBean> list) {
        
            List <TreeBean> ms = null;
            for (TreeBean m : list) {
                // 有父ID
                if (!ToolUtil.isNullOrEmpty(returnMap.get(m.getPid()))) {
                    ms = returnMap.get(m.getPid());
                } else {
                    ms = new ArrayList <TreeBean>();
                }
                ms.add(m);
                returnMap.put(m.getPid(), ms);
            }
            return returnMap;
        }
    
        public List <TreeBean> list2Tree(List <TreeBean> list) {
            List <TreeBean> returnList = new ArrayList <TreeBean>();
            if (map.size() < 1) {
                map = list2MapBean(list);
            }
            if (returnMap.size() < 1) {
                returnMap = list2Map(list);
            }
            for (TreeBean t : list) {
                if (!ToolUtil.isNullOrEmpty(returnMap.get(t.getId()))) {
                    t.setChildren(orderList(returnMap.get(t.getId()), 0));
                }else{
                    t.setParent(false);
                }
                // 无上级时显示
                if(ToolUtil.isNullOrEmpty(t.getPid())){
                    t.setPid("-1");
                }
                // 只获取父级以下的列表
                if("-1".equals(t.getPid())){
                    returnList.add(t);
                }
            }
            return returnList;
        }
    
        public Map <String, TreeBean> list2MapBean (List<TreeBean> list) {
            for (TreeBean t : list) {
                map.put(t.getId(), t);
            }
            return map;
        }
    }
   

    
}
