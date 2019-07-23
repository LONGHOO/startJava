package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.Storage.QiniuStorage;
import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IStrategyService;
import cn.wolfcode.trip.base.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: 十一
 * @Date: 2019-05-24 20:15
 * @Descrption
 **/
@Controller
@RequestMapping("strategy")
public class StrategyController {

    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private IRegionService regionService;

    @RequestMapping("list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model){
        model.addAttribute("pageInfo",strategyService.query(qo));
        model.addAttribute("regions",regionService.listAll(null));
        return "strategy/list";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Result saveOrUpdate(Strategy strategy, MultipartFile file){
        if(null != file && file.getSize() > 0){
            try {
                strategy.setCoverUrl(QiniuStorage.getUploadUrl(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
                return Result.getResult(e.getMessage(), null);
            }
        }
        strategyService.saveOrUpdate(strategy);
        return Result.getResult(null,null);
    }

}
