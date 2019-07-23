package cn.wolfcode.trip.base.domain;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 *功能描述: 游记表的实体类
 * @author 十一
 * @date 2019-05-26 11:12
 */
@Getter
@Setter
public class Travel extends BaseDomain{

    public static final int STATE_NORMAL = 0;
    public static final int STATE_AUDIT = 1;
    public static final int STATE_RELEASE = 2;
    public static final int STATE_REJECT = -1;


    /**
     * 文章状态：未公开
     */
    public static final boolean STATE_PRIVATE  = false;

    /**
     * 文章状态：公开
     */
    public static final boolean STATE_PUBLIC  = true;

    /**
     * 标题
     */
    private String title;

    /**
     * 旅行时间，时区需要加8小时
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date travelTime;

    /**
     * 人均消费
     */
    private String perExpends;

    /**
     * 花费时间
     */
    private Integer days;

    /**
     * 人数
     */
    private Integer person;

    /**
     * 作者
     */
    private User author;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date releaseTime;

    /**
     * 是否公开 0：不公开 1：公开
     */
    private Boolean isPublic;

    /**
     * 旅游地区
     */
    private Region region;

    /**
     * 封面图片
     */
    private String coverUrl;

    /**
     * 最后更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date lastUpdateTime;

    /**
     * 状态：0 未发布，1：发布 2：推荐状态
     */
    private Integer state;

    private TravelContent travelContent;

    public String getStateName() {
        String temp = "";
        if(this.state != null){
            switch(this.state) {
                case -1:
                    temp = "拒绝";
                    break;
                case 0:
                default:
                    temp = "草稿";
                    break;
                case 1:
                    temp = "待审核";
                    break;
                case 2:
                    temp = "已发布";
            }

        }

        return temp;
    }

    public String getPersonName() {
        String temp = "";
        if(null != state){
            switch(this.state) {
                case 1:
                    temp = "一个人的旅行";
                    break;
                case 2:
                    temp = "和父母";
                    break;
                case 3:
                    temp = "和朋友";
                    break;
                case 4:
                    temp = "和同事";
                    break;
                case 5:
                    temp = "和爱人";
                    break;
                default:
                    temp = "和其他";
            }
        }

        return temp;
    }

    public String getJson(){
        Map<String,Object> map = new HashMap<>(16);
        map.put("id",id);
        map.put("title",title);
        map.put("coverUrl", coverUrl);
        return JSON.toJSONString(map);
    }

}