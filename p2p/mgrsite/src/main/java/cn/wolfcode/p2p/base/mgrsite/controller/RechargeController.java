package cn.wolfcode.p2p.base.mgrsite.controller;

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



    @RequestMapping("rechargeOffline")
    public String recharge(Model model, @ModelAttribute("qo") QueryObject qo){
        model.addAttribute("pageInfo", rechargeOfflineService.query(qo));
        model.addAttribute("banks", bankInfoService.selectAll());

        return "rechargeoffline/list";
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


    /**
     *功能描述 充值审核
     * @author 十一
     * @param qo
     * @return cn.wolfcode.p2p.base.util.AjaxResult
     * @date 2019-06-21 19:44
     */
    @LoginRequird
    @RequestMapping("rechargeOffline_audit")
    @ResponseBody
    public AjaxResult rechargeOfflineAudit(Long id,Integer state,String remark){
        AjaxResult result = null;
        try{
            rechargeOfflineService.audit(id,state,remark);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
