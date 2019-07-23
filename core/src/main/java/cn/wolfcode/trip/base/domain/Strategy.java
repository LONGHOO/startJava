package cn.wolfcode.trip.base.domain;

import cn.wolfcode.trip.base.util.BaseMap;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 大攻略
 * @author 十一
 */
@Setter
@Getter
public class Strategy extends BaseDomain{
    /**
     * 普通
     */
    public final static int STATE_COMMON = 0;
    /**
     * 热门
     */
    public final static int STATE_HOT = 1;
    /**
     * 禁用
     */
    public final static int STATE_DISABLE = -1;

    /**
     * 所属地区
     */
    private Region place;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 封面
     */
    private String coverUrl;

    /**
     * 状态
     */
    private Integer state = STATE_COMMON;

    public String getStateName(){
        String temp = "普通";
        if(STATE_HOT==state){
            temp = "热门";
        }else if(STATE_DISABLE==state){
            temp = "禁用";
        }
        return temp;
    }

    public String getJson(){
        Map<String,Object> map = BaseMap.getBaseMap(id,state,title,coverUrl);
        map.put("subTitle",subTitle);
        if(null!=place){
            map.put("placeId",place.getId());
            map.put("placeName",place.getName());
        }
        return JSON.toJSONString(map);
    }



}