package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.mapper.StrategyMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.service.IStrategyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyServiceImpl implements IStrategyService {

    @Autowired
    private StrategyMapper strategyMapper;

    public StrategyServiceImpl() {
    }

    @Override
    public void saveOrUpdate(Strategy strategy) {
        if(strategy.getId()!=null){
            strategyMapper.updateByPrimaryKey(strategy);
        }else{
            strategyMapper.insert(strategy);
        }
    }

    @Override
    public List<Strategy> listAll() {
        return strategyMapper.selectAll();
    }

    @Override
    public List<Strategy> getListByState(Integer state) {
        return strategyMapper.getListByState(state);
    }

    @Override
    public List<Strategy> getHot(StrategyQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Strategy> list = strategyMapper.getHot(qo);
        return list;
    }

    @Override
    public Strategy getById(Long id) {
        return strategyMapper.selectByPrimaryKey(id);
    }


    @Override
    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Strategy> list = strategyMapper.selectForList(qo);
        return new PageInfo(list);
    }

}
