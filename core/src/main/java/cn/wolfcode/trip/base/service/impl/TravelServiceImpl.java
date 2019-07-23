package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.mapper.TravelContentMapper;
import cn.wolfcode.trip.base.mapper.TravelMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-05-19 20:53
 * @Descrption
 **/
@Service
public class TravelServiceImpl implements ITravelService {

    @Autowired
    private TravelMapper travelMapper;
    @Autowired
    private TravelContentMapper contentMapper;

    @Override
    public PageInfo<Travel> query(TravelQueryObject qo) {
       // PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),true,false,null);
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List<Travel> list = travelMapper.query(qo);
        return new PageInfo<>(list);

    }

    @Override
    public void saveOrUpdate(Travel travel) {
        //如果是发布状态
        if(Travel.STATE_AUDIT == travel.getState()){
            travel.setReleaseTime(new Date());
        }
        if(null == travel.getId()){
            travel.setCreateTime(new Date());

            //设置作者id
            travel.setAuthor(UserContext.getUserInSession());
            travelMapper.insert(travel);
            //保存内容主题到travelContent表中
            TravelContent travelContent = travel.getTravelContent();
            travelContent.setId(travel.getId());
            contentMapper.insert(travelContent);

        }else{
            travelMapper.updateByPrimaryKey(travel);
            TravelContent travelContent = travel.getTravelContent();
            contentMapper.updateByPrimaryKey(travelContent);
        }
    }

    @Override
    public Travel getById(Long id) {
        return travelMapper.selectByPrimaryKey(id);
    }

    @Override
    public TravelContent getContentById(Long id) {
        TravelContent travelContent = contentMapper.selectByPrimaryKey(id);
        return travelContent;
    }

    @Override
    public void changeState(Travel travel) {
        travelMapper.changeState(travel);
    }

    @Override
    public PageInfo<Travel> travelList(TravelQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List<Travel> list = travelMapper.travelList(qo);
        return new PageInfo<>(list);
    }
}
