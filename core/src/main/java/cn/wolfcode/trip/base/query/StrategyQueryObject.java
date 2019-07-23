package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: 十一
 * @Date: 2019-05-25 21:58
 * @Descrption
 **/
@Getter
@Setter
public class StrategyQueryObject extends QueryObject {

    private Long regionId;

    private Integer state;

    private Integer type;

    private Boolean disable;

    private Long strategyId;
}
