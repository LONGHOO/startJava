package cn.wolfcode.trip.base.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
/**
 * 地区实体类
 * @author 十一
 */
@Getter
@Setter
public class Region  extends BaseDomain{

    public static final int REGION_STATUS_DEFAULT = 0;
    public static final int REGION_STATUS_RECOMMEND= 1;
    public static final int REGION_STATUS_DISABLE = -1;
    /**
     * 地区名称
     */
    private String name;

    /**
     * 上级地区信息
     */
    private Region parent;

    /**
     * 状态 0：不推荐 1：推荐 -1:禁用
     */
    private Integer state = 0;

    /**
     * 功能描述: 封装为json数据
     * @date:   2019-05-21 14:40
     */
    public Map toTreeMap(){
       Map<String,Object> map =  new HashMap<>(16);
       if(state==REGION_STATUS_RECOMMEND){
           map.put("tags",new String[]{"推荐"});
       }
       map.put("text",name);
       map.put("id",id);
       map.put("lazyLoad",true);
       return map;
    }

    public String getJson(){
        Map<String,Object> map = new HashMap<>(16);
        map.put("id",id);
        map.put("name",name);
        map.put("state",state);
        if(null!=parent){
            map.put("parentId",parent.getId());
            map.put("parentName",parent.getName());
        }
        return JSON.toJSONString(map);
    }

}