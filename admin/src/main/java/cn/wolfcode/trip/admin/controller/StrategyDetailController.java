package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.Storage.QiniuStorage;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyCatalogService;
import cn.wolfcode.trip.base.service.IStrategyContentService;
import cn.wolfcode.trip.base.service.IStrategyDetailService;
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
@RequestMapping("strategyDetail")
public class StrategyDetailController {

    @Autowired
    private IStrategyDetailService strategyDetailService;
    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private IStrategyContentService strategyContentService;

    @RequestMapping("list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model){
        model.addAttribute("pageInfo",strategyDetailService.query(qo));
        model.addAttribute("strategy",strategyService.listAll());
        return "strategyDetail/list";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Result saveOrUpdate(StrategyDetail strategyDetail, MultipartFile file){
        if(null != file && file.getSize() > 0){
            try {
                strategyDetail.setCoverUrl(QiniuStorage.getUploadUrl(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
                return Result.getResult(e.getMessage(), null);
            }
        }
        //如果没有选择序号，则查询当前攻略的最大序号并加1
        if(null == strategyDetail.getSequence()){
            int sequence = strategyDetailService.getMaxSequenceByCatalogId(strategyDetail.getCatalog().getId());
            strategyDetail.setSequence(sequence+1);
        }
        strategyDetailService.saveOrUpdate(strategyDetail);
        return Result.getResult(null,null);
    }
    @RequestMapping("getContentById")
    @ResponseBody
    public Result getContentById(Long id){
        return Result.getResult(null,strategyContentService.getById(id));
    }

}
