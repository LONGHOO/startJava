package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemDictionary extends BaseDomain{

    private String sn;

    private String title;

    private String intro;

//    @Override
//    public String getJson(){
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", id);
//        map.put("sn", sn);
//        map.put("title", title);
//        map.put("intro", intro);
//        return JSON.toJSONString(map);
//    }

}