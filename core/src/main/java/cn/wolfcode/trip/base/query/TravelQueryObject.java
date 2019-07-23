package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: 十一
 * @Date: 2019-05-21 19:10
 * @Descrption
 **/
@Getter
@Setter
public class TravelQueryObject extends QueryObject {

    /**
     * 排序字段
     */
    private String orderBy;

    private Long authorId;

    private Integer state;

    private Boolean isPublic;

    private Integer travelId;

}
