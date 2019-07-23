package cn.wolfcode.p2p.base.service;

import cn.wolfcode.p2p.base.domain.OrderTime;
import cn.wolfcode.p2p.base.domain.VideoAuth;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-06-18 19:13
 * @Descrption
 **/
public interface IOrderTimeService {

    int deleteByPrimaryKey(Long id);

    int insert(OrderTime record);

    OrderTime selectByPrimaryKey(Long id);

    List<OrderTime> selectAll();

    int updateByPrimaryKey(OrderTime record);


}
