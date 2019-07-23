package cn.wolfcode.p2p.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SystemDictionary extends BaseDomain {

    private String sn;

    private String title;

    public String getJsonString(){
        Map<Object, Object> map = new HashMap<>(16);
        map.put("id",id);
        map.put("sn", sn);
        map.put("title",title);
        return JSONUtils.toJSONString(map);
    }
}