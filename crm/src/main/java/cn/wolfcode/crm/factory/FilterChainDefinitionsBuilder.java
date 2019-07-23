package cn.wolfcode.crm.factory;

import java.util.LinkedHashMap;

/**
 * @Author: 十一
 * @Date: 2019-05-08 21:39
 * @Descrption
 **/
public class FilterChainDefinitionsBuilder {

    public LinkedHashMap<String, String> filterChainDefinitions(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/js/**", "anon");
        map.put("/css/**", "anon");
        map.put("/images/**", "anon");
        map.put("/logout.do", "logout");
        map.put("/**", "authc");
        return map;
    }
}
