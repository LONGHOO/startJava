package cn.wolfcode.crud.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class Systemdictionaryitem extends BaseDomain{

    private Long parentId;

    private String title;

    private Integer sequence;

    public String getJson(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("sequence",sequence);
        //map集合转json字符串返回
        return JSON.toJSONString(map);
    }

}