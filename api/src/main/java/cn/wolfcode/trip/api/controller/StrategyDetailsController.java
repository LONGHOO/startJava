package cn.wolfcode.trip.api.controller;

import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.service.IStrategyDetailService;
import cn.wolfcode.trip.base.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 十一
 * @Date: 2019-05-25 21:32
 * @Descrption
 **/
@RestController
@RequestMapping("strategyDetails")
public class StrategyDetailsController {

    @Autowired
    private IStrategyDetailService strategyDetailservice;

    @GetMapping(value = "{id}")
    public Result getStrategyInfo(@PathVariable Long id) {
        StrategyDetail detail = strategyDetailservice.getById(id);
        return Result.getResult(null, detail);
    }

}
