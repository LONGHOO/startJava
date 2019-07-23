package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 十一
 * @Date: 2019-05-19 20:41
 * @Descrption
 **/
@Controller
@RequestMapping("region")
public class RegionController {

    @Autowired
    private IRegionService regionService;

    @RequestMapping("list")
    public String list(){
        return "region/list";
    }

    /**
     *
     * 功能描述:
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */

    @RequestMapping("listByParentId")
    @ResponseBody
    public List listByParentId(String type,Long parentId){
        List<Region> list = regionService.query(parentId);
        List<Map> result = new ArrayList<>();
        if(null == type){
            return list;
        }else{
            for (Region region : list) {
                //如果当前景点被禁用，则不添加
                if(region.getState().equals(Region.REGION_STATUS_DISABLE)){
                    continue;
                }
                result.add(region.toTreeMap());
            }
            return result;
        }
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Result saveOrUpdate(Region region){
        regionService.saveOrUpdate(region);
        return Result.getResult(null,region);
    }
    @ResponseBody
    @RequestMapping("changeState")
    public Result changeState(Region region){
        regionService.changeState(region);
        return Result.getResult(null,region);
    }
}
