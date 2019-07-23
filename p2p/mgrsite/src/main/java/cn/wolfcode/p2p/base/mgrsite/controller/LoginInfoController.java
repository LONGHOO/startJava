package cn.wolfcode.p2p.base.mgrsite.controller;

import cn.wolfcode.p2p.base.domain.LoginInfo;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.service.ILoginInfoService;
import cn.wolfcode.p2p.base.service.IVerifyCodeService;
import cn.wolfcode.p2p.base.util.AjaxResult;
import cn.wolfcode.p2p.base.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: 十一
 * @Date: 2019-06-12 16:44
 * @Descrption
 **/
@Controller
public class LoginInfoController {

    @Autowired
    private ILoginInfoService loginInfoService;

    @Autowired
    private IVerifyCodeService verifyCodeService;

    /**
     *功能描述 用户登陆
     * @author 十一
     * @param username, password, request
     * @return cn.wolfcode.p2p.base.util.AjaxResult
     * @date 2019-06-14 14:32
     */
    @RequestMapping("mgrLogin")
    @ResponseBody
    public AjaxResult mgrLogin(String username, String password){
        AjaxResult result = null;
        try{
            LoginInfo login = loginInfoService.login(username, MD5.encode(password),LoginInfo.USERTYPE_MANAGER);
            if(null == login){
                throw new DisplayException("用户名或密码错误");
            }
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }
}
