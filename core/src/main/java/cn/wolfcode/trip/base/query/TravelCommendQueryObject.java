package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-05-24 13:08
 * @Descrption
 **/
@Getter
@Setter
public class TravelCommendQueryObject extends QueryObject {

    private String orderBy;

    private int type = -1;

    private Date schedule;
}
