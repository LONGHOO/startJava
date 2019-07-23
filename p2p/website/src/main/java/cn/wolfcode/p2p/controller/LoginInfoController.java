package cn.wolfcode.p2p.controller;

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
     *功能描述 注册用户
     * @author 十一
     * @param username, verifycode, password, confirmPwd
     * @return cn.wolfcode.p2p.base.util.AjaxResult
     * @date 2019-06-14 14:31
     */
    @RequestMapping("register")
    @ResponseBody
    public AjaxResult register(String username,String verifycode,String password,String confirmPwd){
        AjaxResult ajaxResult = null;
        try{
            loginInfoService.register(username,verifycode,password,confirmPwd);
            ajaxResult = new AjaxResult();
        }catch(DisplayException e){
            e.printStackTrace();
            ajaxResult = new AjaxResult(false,e.getMessage());
        }catch(RuntimeException e){
            e.printStackTrace();
            ajaxResult = new AjaxResult(false, "系统异常。。。。");
        }
        return ajaxResult;
    }

    /**
     *功能描述  发送短信验证码
     * @author 十一
     * @param phoneNumber 用户名：电话号码
     * @return cn.wolfcode.p2p.base.util.AjaxResult
     * @date 2019-06-14 14:31
     */
    @RequestMapping("sendVerifyCode")
    @ResponseBody
    public AjaxResult sendVerifyCode(String phoneNumber){
        AjaxResult ajaxResult = null;
        try{
            verifyCodeService.sendVerifyCode(phoneNumber);
            ajaxResult = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            ajaxResult = new AjaxResult(false,e.getMessage());
        }
        return ajaxResult;
    }

    /**
     *功能描述 判断是否存在用户
     * @author 十一
     * @param username
     * @return java.lang.Boolean
     * @date 2019-06-14 14:32
     */
    @RequestMapping("existUsername")
    @ResponseBody
    public Boolean existUsername(String username){
        return !(loginInfoService.countByUsername(username) > 0);
    }

    /**
     *功能描述 用户登陆
     * @author 十一
     * @param username, password, request
     * @return cn.wolfcode.p2p.base.util.AjaxResult
     * @date 2019-06-14 14:32
     */
    @RequestMapping("userLogin")
    @ResponseBody
    public AjaxResult userLogin(String username, String password, HttpServletRequest request){
        AjaxResult result = null;
        try{
            LoginInfo login = loginInfoService.login(username, MD5.encode(password), LoginInfo.STATE_NORMAL);
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
