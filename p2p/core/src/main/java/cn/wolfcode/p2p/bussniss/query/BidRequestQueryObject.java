package cn.wolfcode.p2p.bussniss.query;

import cn.wolfcode.p2p.base.query.QueryObject;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: 十一
 * @Date: 2019-06-19 18:25
 * @Descrption
 **/
@Getter
@Setter
public class BidRequestQueryObject extends QueryObject {

    //状态
    private Integer state;

    //批量状态
    private int[] states;

    //标状态
    private int bidRequestState = -1;

    private int type = -1 ;
}
