package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.mapper.StrategyCatalogMapper;
import cn.wolfcode.trip.base.query.StrategyCatalogQueryObject;
import cn.wolfcode.trip.base.service.IStrategyCatalogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyCatalogServiceImpl implements IStrategyCatalogService {

    @Autowired
    private StrategyCatalogMapper strategyCatalogMapper;

    @Override
    public void saveOrUpdate(StrategyCatalog strategyCatalog) {
      
        if(strategyCatalog.getId()!=null){
            strategyCatalogMapper.updateByPrimaryKey(strategyCatalog);
        }else{
            strategyCatalogMapper.insert(strategyCatalog);
        }
    }

    @Override
    public int getMaxSequenceByStrategyId(Long id) {
        return strategyCatalogMapper.getMaxSequenceByStrategyId(id);
    }

    @Override
    public List<StrategyCatalog> queryForStrategyId(Long strategyId) {
        return strategyCatalogMapper.queryForStrategyId(strategyId);
    }

    @Override
    public PageInfo query(StrategyCatalogQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List<StrategyCatalog> list = strategyCatalogMapper.selectForList(qo);
        return new PageInfo(list);
    }

}
