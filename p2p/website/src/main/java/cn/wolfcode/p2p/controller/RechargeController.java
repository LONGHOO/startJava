package cn.wolfcode.p2p.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.util.AjaxResult;
import cn.wolfcode.p2p.bussniss.domain.RechargeOffline;
import cn.wolfcode.p2p.bussniss.service.IBankInfoService;
import cn.wolfcode.p2p.bussniss.service.IRechargeOfflineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-06-21 18:31
 * @Descrption
 **/
@Controller
public class RechargeController {

    @Autowired
    private IBankInfoService bankInfoService;

    @Autowired
    private IRechargeOfflineService rechargeOfflineService;

    @RequestMapping("recharge")
    public String recharge(Model model){
        model.addAttribute("banks", bankInfoService.selectAll());
        return "recharge";
    }

    @LoginRequird
    @RequestMapping("recharge_save")
    @ResponseBody
    public AjaxResult rechargeSave(RechargeOffline rechargeOffline){
        AjaxResult result = null;
        try{
            rechargeOfflineService.saveOrUpdate(rechargeOffline);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

}
