package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Author: 十一
 * @Date: 2019-05-17 18:59
 * @Descrption
 **/
public interface ITravelCommendService {

    /**
     * 分页查询
     */
    PageInfo<TravelCommend> queryList(TravelCommendQueryObject qo);

    void saveOrUpdate(TravelCommend travelCommend);

    TravelCommend getById(Long id);

    PageInfo<Map> query(TravelCommendQueryObject qo);

}
