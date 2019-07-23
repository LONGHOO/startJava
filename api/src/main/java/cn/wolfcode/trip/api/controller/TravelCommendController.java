package cn.wolfcode.trip.api.controller;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import cn.wolfcode.trip.base.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-05-24 19:30
 * @Descrption
 **/
@RestController
@RequestMapping("travelCommends")
public class TravelCommendController {


    @Autowired
    private ITravelCommendService travelCommendService;

    @RequestMapping("weeks")
    public Result weeks(TravelCommendQueryObject qo){
        qo.setType(TravelCommend.TYPE_WEEK);
        qo.setSchedule(new Date());
        qo.setOrderBy("tc.schedule desc");
        return Result.getResult(null,travelCommendService.query(qo));
    }

    @GetMapping("monthTop")
    public Result monthTop(TravelCommendQueryObject qo){
        qo.setType(TravelCommend.TYPE_MONTH);
        qo.setOrderBy("tc.schedule desc");
        return Result.getResult(null, travelCommendService.query(qo));
    }

    @RequestMapping("type/{type}")
    public Result list(TravelCommendQueryObject qo){
        qo.setOrderBy("schedule desc");
        List<TravelCommend> list = travelCommendService.queryList(qo).getList();
        if(!list.isEmpty()){
            return Result.getResult(null, list.get(0));
        }
        return Result.getResult("未查询到数据",null);
    }
}
