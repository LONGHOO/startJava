package cn.wolfcode.trip.base.domain;

import cn.wolfcode.trip.base.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
/**
 *功能描述: 攻略评论实体类
 * @author 十一
 * @date 2019-05-27 19:48
 */
@Getter
@Setter
public class StrategyComment extends BaseDomain{

    private User user;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    private String content;

    private String imgUrls;

    private Integer starNum;

    private Strategy strategy;

    private Integer state = 0;

    private Date commendTime;

    public String[] getImages(){
        if(StringUtils.hasLength(this.imgUrls)){
            return this.imgUrls.split(";");
        }
        return null;
    }
}