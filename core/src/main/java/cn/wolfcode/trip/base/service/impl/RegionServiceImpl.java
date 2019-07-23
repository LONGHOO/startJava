package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.mapper.RegionMapper;
import cn.wolfcode.trip.base.service.IRegionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 十一
 * @Date: 2019-05-19 20:53
 * @Descrption
 **/
@Service
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private RegionMapper regionMapper;
    @Override
    public List<Region> query(Long parentId) {
        return regionMapper.selectByParentId(parentId);
    }

    @Override
    public void saveOrUpdate(Region region) {
        if(null != region.getId()){
            regionMapper.updateByPrimaryKey(region);
        }else{
            regionMapper.insert(region);
        }
    }

    @Override
    public void changeState(Region region) {
        regionMapper.changeState(region);
    }

    @Override
    public List<Region> listAll(Integer state) {
        return regionMapper.selectAll(state);
    }
}
