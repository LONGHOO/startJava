package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.StrategyCatalogQueryObject;
import cn.wolfcode.trip.base.service.IStrategyCatalogService;
import cn.wolfcode.trip.base.service.IStrategyService;
import cn.wolfcode.trip.base.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-05-24 21:11
 * @Descrption
 **/
@Controller
@RequestMapping("strategyCatalog")
public class StrategyCatalogController {


    @Autowired
    private IStrategyCatalogService strategyCatalogService;

    @Autowired
    private IStrategyService strategyService;

    @RequestMapping("list")
    public String list(@ModelAttribute("qo") StrategyCatalogQueryObject qo, Model model){
        qo.setOrderBy("sc.sequence asc");
        model.addAttribute("strategys",strategyService.listAll());
        model.addAttribute("pageInfo", strategyCatalogService.query(qo));
        return "strategyCatalog/list";
    }
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Result saveOrUpdate(StrategyCatalog strategyCatalog){
        //如果没有选择序号，则查询当前攻略的最大序号并加1
        if(null == strategyCatalog.getSequence()){
            int sequence = strategyCatalogService.getMaxSequenceByStrategyId(strategyCatalog.getStrategy().getId());
            strategyCatalog.setSequence(sequence);
        }
        strategyCatalogService.saveOrUpdate(strategyCatalog);
        return Result.getResult(null, null);
    }

    @ResponseBody
    @RequestMapping("queryByStrategyId")
    public Result queryByStrategyId(Long strategyId){
        return Result.getResult(null,strategyCatalogService.queryForStrategyId(strategyId));
    }
}
