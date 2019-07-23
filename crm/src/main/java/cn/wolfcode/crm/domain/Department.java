package cn.wolfcode.crm.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Department extends BaseDomain {
    private String name;

    private String sn;
//
//
//    @Override
//    public String getJson(){
//        Map<String, Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("name",name);
//        map.put("sn",sn);
//        Object obj = JSON.toJSON(map);
//        return obj.toString();
//    }
}