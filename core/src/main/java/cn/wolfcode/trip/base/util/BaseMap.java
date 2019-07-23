package cn.wolfcode.trip.base.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 十一
 * @Date: 2019-05-26 11:19
 * @Descrption 抽取常用的字段封装到Map中
 **/
public class BaseMap {

    private BaseMap(){}

    public static Map<String,Object> getBaseMap(Object id,Object state,Object title,Object coverUrl){
        Map<String,Object> map = new HashMap<>(16);
        map.put("id",id);
        map.put("state",state);
        map.put("title",title);
        map.put("coverUrl",coverUrl);
        return map;
    }
}
