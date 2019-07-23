package cn.wolfcode.crud.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter@Setter
public class Systemdictionary extends BaseDomain{

    private String sn;

    private String title;

    private String intro;

    public String getJson(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("sn",sn);
        map.put("title",title);
        map.put("intro",intro);
        //map集合转json字符串返回
        return JSON.toJSONString(map);
    }
}