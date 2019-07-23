package cn.wolfcode.p2p.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SystemDictionaryItem  extends BaseDomain {

    private Long parentId;

    private String title;

    private Integer sequence;

   public String getJsonString(){
       Map<Object, Object> map = new HashMap<>(16);
       map.put("id",id);
       map.put("parentId",parentId);
       map.put("title",title);
       map.put("sequence",sequence);

       return JSONUtils.toJSONString(map);
   }

}