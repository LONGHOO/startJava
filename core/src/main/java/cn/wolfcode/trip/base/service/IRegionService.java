package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Region;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-05-17 18:59
 * @Descrption
 **/
public interface IRegionService {

    List<Region> query(Long parentId);

    void saveOrUpdate(Region region);

    void changeState(Region region);

    List<Region> listAll(Integer state);
}
