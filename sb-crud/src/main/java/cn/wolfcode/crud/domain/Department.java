package cn.wolfcode.crud.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter@Setter
public class Department extends BaseDomain{

    private String name;

    private String sn;

    public String getJson(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("sn",sn);
        //map集合转json字符串返回
        return JSON.toJSONString(map);
    }
}