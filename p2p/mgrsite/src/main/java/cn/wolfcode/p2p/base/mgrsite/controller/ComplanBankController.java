package cn.wolfcode.p2p.base.mgrsite.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.util.AjaxResult;
import cn.wolfcode.p2p.bussniss.domain.PlatformBankInfo;
import cn.wolfcode.p2p.bussniss.service.IBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-06-21 18:34
 * @Descrption
 **/
@Controller
public class ComplanBankController {

    @Autowired
    private IBankInfoService bankInfoService;

    @RequestMapping("companyBank_list")
    public String companyBankList(Model model, @ModelAttribute("qo") QueryObject qo) {
        model.addAttribute("pageInfo", bankInfoService.query(qo));
        return "platformbankinfo/list";
    }

    @LoginRequird
    @RequestMapping("companyBank_update")
    @ResponseBody
    public AjaxResult companyBankUpdate(PlatformBankInfo bankInfo){
        AjaxResult result = null;
        try{
            bankInfoService.saveOrUpdate(bankInfo);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
