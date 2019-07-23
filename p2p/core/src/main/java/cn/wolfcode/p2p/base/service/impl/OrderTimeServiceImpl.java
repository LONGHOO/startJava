package cn.wolfcode.p2p.base.service.impl;

import cn.wolfcode.p2p.base.domain.OrderTime;
import cn.wolfcode.p2p.base.mapper.OrderTimeMapper;
import cn.wolfcode.p2p.base.service.IOrderTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-06-18 19:14
 * @Descrption
 **/
@Service
public class OrderTimeServiceImpl implements IOrderTimeService {

    @Autowired
    private OrderTimeMapper orderTimeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return orderTimeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderTime record) {
        return orderTimeMapper.insert(record);
    }

    @Override
    public OrderTime selectByPrimaryKey(Long id) {
        return orderTimeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderTime> selectAll() {
        return orderTimeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(OrderTime record) {
        return orderTimeMapper.updateByPrimaryKey(record);
    }

}
