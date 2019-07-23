package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.mapper.StrategyContentMapper;
import cn.wolfcode.trip.base.mapper.StrategyDetailMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StrategyDetailServiceImpl implements IStrategyDetailService {

    @Autowired
    private StrategyDetailMapper strategyDetailMapper;

    @Autowired
    private StrategyContentMapper contentMapper;

    @Override
    public void saveOrUpdate(StrategyDetail strategyDetail) {
        StrategyContent content = strategyDetail.getStrategyContent();
        if(strategyDetail.getState().equals(StrategyDetail.STATE_RELEASE)){
            strategyDetail.setReleaseTime(new Date());
        }
        if(strategyDetail.getId()!=null){
            strategyDetailMapper.updateByPrimaryKey(strategyDetail);
            content.setId(strategyDetail.getId());
            contentMapper.updateByPrimaryKey(content);
        }else{
            strategyDetailMapper.insert(strategyDetail);
            content.setId(strategyDetail.getId());
            contentMapper.insert(content);
        }


    }

    @Override
    public int getMaxSequenceByCatalogId(Long id) {
        return strategyDetailMapper.getMaxSequenceByCatalogId(id);
    }

    @Override
    public StrategyDetail getById(Long id) {
        return strategyDetailMapper.selectByPrimaryKey(id);
    }


    @Override
    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List<StrategyDetail> list = strategyDetailMapper.selectForList(qo);
        return new PageInfo(list);
    }

}
