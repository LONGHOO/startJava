package cn.wolfcode.trip.base.domain;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 游记推荐实体类
 * @author 十一
 */
@Getter
@Setter
public class TravelCommend extends BaseDomain{

    /**
     * 周推荐
     */
    public static final Integer TYPE_WEEK = 0;
    /**
     * 月推荐
     */
    public static final Integer TYPE_MONTH = 1;
    /**
     * 攻略推荐
     */
    public static final Integer TYPE_STRATEGY = 2;


    /**
     * 游记
     */
    private Travel travel;

    /**
     * 标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subTitle;
    /**
     * 封面url
     */
    private String coverUrl;
    /**
     * 推荐时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date schedule;
    /**
     * 推荐类型
     */
    private Integer type;

    public String getTypeName(){
        String name = "周推荐";
        if(TYPE_MONTH.equals(type)){
            name = "月推荐";
        }else if(TYPE_STRATEGY.equals(type)){
            name = "攻略推荐";
        }
        return name;
    }

    public String getJson(){
        Map<String,Object> map = new HashMap<>(16);
        map.put("id",id);
        map.put("title",title);
        map.put("subTitle",subTitle);
        map.put("coverUrl", coverUrl);
        map.put("travelId", travel.id);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        map.put("schedule", format.format(schedule));
        map.put("type",type);
        return JSON.toJSONString(map);
    }




}