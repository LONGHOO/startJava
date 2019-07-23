package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import com.github.pagehelper.PageInfo;

/**
 * @Author: 十一
 * @Date: 2019-05-17 18:59
 * @Descrption
 **/
public interface ITravelService {

    /**
     * 分页查询
     */
    PageInfo<Travel> query(TravelQueryObject qo);

    void saveOrUpdate(Travel travel);

    Travel getById(Long id);

    TravelContent getContentById(Long id);

    void changeState(Travel travel);

    PageInfo<Travel> travelList(TravelQueryObject qo);
}
