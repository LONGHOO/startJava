package cn.wolfcode.p2p.base.mgrsite.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.domain.RealAuth;
import cn.wolfcode.p2p.base.domain.UserInfo;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.query.RealAuthQueryObject;
import cn.wolfcode.p2p.base.service.IRealAuthService;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.util.AjaxResult;
import cn.wolfcode.p2p.base.util.BitStateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-06-16 21:13
 * @Descrption
 **/
@Controller
public class RealAuthController {

    @Autowired
    private IRealAuthService realAuthService;
    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("realAuth")
    @LoginRequird
    public String realAuth(Model model, @ModelAttribute("qo") RealAuthQueryObject qo){
        model.addAttribute("pageInfo",realAuthService.query(qo));
        return "realAuth/list";
    }

    @LoginRequird
    @RequestMapping("realAuth_save")
    @ResponseBody
    public AjaxResult realAuthSave(RealAuth realAuth){
        AjaxResult result = null;
        try{
            realAuthService.saveOrUpdate(realAuth);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

    @LoginRequird
    @RequestMapping("realAuth_audit")
    @ResponseBody
    public AjaxResult realAuthAudit(RealAuth realAuth){
        AjaxResult result = null;
        try{
            realAuthService.audit(realAuth);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

}
