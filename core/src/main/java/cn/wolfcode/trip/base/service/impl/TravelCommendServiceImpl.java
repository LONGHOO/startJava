package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.mapper.TravelCommendMapper;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import com.github.pagehelper.PageHelper;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 十一
 * @Date: 2019-05-19 20:53
 * @Descrption
 **/
@Service
public class TravelCommendServiceImpl implements ITravelCommendService {

    @Autowired
    private TravelCommendMapper travelCommendMapper;
    @Autowired
    private TravelCommendMapper contentMapper;

    @Override
    public PageInfo<TravelCommend> queryList(TravelCommendQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List<TravelCommend> list = travelCommendMapper.query(qo);
        return new PageInfo<>(list);

    }

    @Override
    public void saveOrUpdate(TravelCommend travelCommend) {
        if(null != travelCommend.getId()){
            travelCommendMapper.updateByPrimaryKey(travelCommend);
        }else{
            travelCommendMapper.insert(travelCommend);
        }
    }

    @Override
    public TravelCommend getById(Long id) {
        return travelCommendMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Map> query(TravelCommendQueryObject qo) {
        List<Map> query = null;
        if(qo.getType() == TravelCommend.TYPE_WEEK){
            PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
            query = travelCommendMapper.queryForList(qo);
        }
        if(qo.getType() == TravelCommend.TYPE_MONTH){
            PageHelper.startPage(0,1,qo.getOrderBy());
            query = travelCommendMapper.queryForList(qo);
        }

        return new PageInfo<>(query);
    }


}
