package cn.wolfcode.trip.base.domain;

import cn.wolfcode.trip.base.util.BaseMap;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  攻略文章
 * @author 十一
 */
@Setter
@JsonIgnoreProperties("handler")
@Getter
public class StrategyDetail extends BaseDomain{
    /**
     * 草稿
    **/
    public static final int STATE_COMMON = 0;

    /**
     * 发布
    **/
    public static final int STATE_RELEASE = 1;

    /**
     * 禁用
    **/
    public static final int STATE_DISABLE= -1;

    /**
     * 标题
    **/
    private String title;

    /**
     * 创建时间
    **/
    private Date createTime;

    /**
     * 发布时间
    **/
    private Date releaseTime;

    /**
     * 序号
    **/
    private Integer sequence;

    /**
     * 所属目录
    **/
    private StrategyCatalog catalog;

    /**
     * 封面
     **/
    private String coverUrl;

    /**
     * 状态
     **/
    private Integer state = STATE_COMMON;

    /**
     * 攻略文章内容
     **/
    private StrategyContent strategyContent;

    public String getStateName(){
        String temp = "草稿";
        if(STATE_RELEASE==state){
            temp = "发布";
        }else if(STATE_DISABLE==state){
            temp = "禁用";
        }
        return temp;
    }

    public String getJson(){
        Map<String,Object> map = BaseMap.getBaseMap(id,state,title,coverUrl);
        map.put("sequence",sequence);
        if(null!=catalog){
            map.put("catalogId",catalog.getId());
            map.put("catalogName",catalog.getName());
            Strategy strategy = catalog.getStrategy();
            if(null != catalog.getStrategy()){
                map.put("strategyId", strategy.getId());
                map.put("strategyTitle",strategy.getTitle());
            }
        }
        return JSON.toJSONString(map);
    }

    

}