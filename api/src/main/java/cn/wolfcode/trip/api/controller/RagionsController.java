package cn.wolfcode.trip.api.controller;

import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 十一
 * @Date: 2019-05-22 19:21
 * @Descrption
 **/
@RestController
@RequestMapping("ragions")
public class RagionsController {

    @Autowired
    private IRegionService ragionService;

    @GetMapping
    public Result ragions(){
        return Result.getResult(null,ragionService.listAll(null));
    }

    @GetMapping("state/{state}")
    public Result ragions(@PathVariable("state") Integer state){
        return Result.getResult(null,ragionService.listAll(state));
    }
}
