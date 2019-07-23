package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.mapper.StrategyContentMapper;
import cn.wolfcode.trip.base.mapper.StrategyMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyContentService;
import cn.wolfcode.trip.base.service.IStrategyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyContentServiceImpl implements IStrategyContentService {

    @Autowired
    private StrategyContentMapper contentMapper;
    @Override
    public PageInfo query(QueryObject qo) {
        return null;
    }

    @Override
    public void saveOrUpdate(StrategyContent content) {
        if(null != content.getId()){
            contentMapper.updateByPrimaryKey(content);
        }else{
            contentMapper.insert(content);
        }
    }

    @Override
    public List<StrategyContent> listAll() {
        return contentMapper.selectAll();
    }

    @Override
    public StrategyContent getById(Long id) {
        return contentMapper.getById(id);
    }
}
