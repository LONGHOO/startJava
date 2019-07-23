package cn.wolfcode.trip.api.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 十一
 * @Date: 2019-05-21 19:17
 * @Descrption
 **/
@RestController
@RequestMapping("travels")
public class TravelController {

    @Autowired
    private ITravelService travelService;

    @PostMapping
    public Result save(Travel travel) {
        this.travelService.saveOrUpdate(travel);
        return Result.getResult(null, null);
    }

    @GetMapping("list")
    public PageInfo<Travel> travelList(TravelQueryObject qo){
        qo.setState(Travel.STATE_RELEASE);
        qo.setIsPublic(true);
        qo.setOrderBy("t.lastUpdateTime asc");
        return travelService.travelList(qo);
    }

    @PutMapping({"/{id}"})
    public Result update(Travel travel) {
        this.travelService.saveOrUpdate(travel);
        return Result.getResult(null, null);
    }
    @GetMapping({"/{id}"})
    public Result getById(@PathVariable Long id) {
        return Result.getResult(null,travelService.getById(id));
    }

}
