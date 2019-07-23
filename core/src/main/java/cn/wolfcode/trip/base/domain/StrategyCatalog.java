package cn.wolfcode.trip.base.domain;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 *  攻略目录
 * @author 十一
 */
@Setter
@Getter
@JsonIgnoreProperties("handler")
public class StrategyCatalog extends BaseDomain{

    /**
     * 名称
     */
    private String name;

    /**
     * 所属攻略
     */
    private Strategy strategy;

    /**
     * 序号
     */
    private Integer sequence;

    /**
     * 状态
     */
    private Boolean state;

    /**
     * 攻略文章
    **/
    private List<StrategyDetail> details;


    public String getJson(){
        Map<String,Object> map = new HashMap<>(16);
        map.put("id",id);
        map.put("state",state);
        map.put("name",name);
        map.put("sequence",sequence);
        if(null!=strategy){
            map.put("strategyId",strategy.getId());
            map.put("strategyTitle",strategy.getTitle());
        }
        return JSON.toJSONString(map);
    }


}



