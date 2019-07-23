package cn.wolfcode.p2p.controller;

import cn.wolfcode.p2p.base.service.IAccountService;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.util.UserContext;
import cn.wolfcode.p2p.bussniss.service.IExpAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 十一
 * @Date: 2019-06-15 12:36
 * @Descrption
 **/
@Controller
public class PersonalController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IExpAccountService expAccountService;

    @RequestMapping("personal")
    public String personal(Model model){
        if(UserContext.getCurrentUser() != null){
            model.addAttribute("account", accountService.getCurrent());
            model.addAttribute("userInfo", userInfoService.getCurrent());
            model.addAttribute("expAccount", expAccountService.getCureent());
        }
        return "personal";
    }
}
