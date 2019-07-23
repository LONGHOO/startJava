package cn.wolfcode.p2p.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.domain.RealAuth;
import cn.wolfcode.p2p.base.domain.UserInfo;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.service.IRealAuthService;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.util.AjaxResult;
import cn.wolfcode.p2p.base.util.BitStateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String realAuth(Model model){
        //如果当前用户已经实名认证过，则跳转到realAuth_result界面
        UserInfo userInfo = userInfoService.getCurrent();
        if(BitStateUtil.hasState(userInfo.getBitState(),BitStateUtil.HAS_REALAUTH)){
            model.addAttribute("auditing",false);
            model.addAttribute("realAuth",realAuthService.getCurrent());
            return "realAuth_result";
        }
        //如果当前用户没有实名认证，但是realAuthId不为null,表示正在审核，跳转到realAuth_result界面
        if(null != userInfo.getRealAuthId()){
            model.addAttribute("auditing",true);
            return "realAuth_result";
        }
        //否则跳转到realAuth界面

        return "realAuth";
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

}
