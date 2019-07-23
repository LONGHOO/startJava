package cn.wolfcode.p2p.base.util;

import cn.wolfcode.p2p.base.domain.LoginInfo;
import cn.wolfcode.p2p.base.vo.VerifyCodeVo;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: 十一
 * @Date: 2019-05-04 15:00
 * @Descrption
 **/
public class UserContext {

    private UserContext(){}

    public static final String LOGININFO_IN_SESSION =  "LOGININFO_IN_SESSION";
    public static final String VERIFYCODE_IN_SESSION =  "VERIFYCODE_IN_SESSION";

    /**
     *
     * 功能描述:
     * @param:  获取到当前会话的session
     * @return:
     * @auther: 十一
     * @date:
     */
    public static HttpSession getSession(){
        return getRequest().getSession();
    }

    /**
     *功能描述 获取请求对象
     * @author 十一
     * @return javax.servlet.http.HttpServletRequest
     * @date 2019-06-15 18:38
     */
    public static HttpServletRequest getRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes req = (ServletRequestAttributes)requestAttributes;
        return req.getRequest();
    }

    /**
     *
     * 功能描述:将当前用户的信息设置到Session中
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    public static void setLoginInfoInSession(LoginInfo loginInfo){
        getSession().setAttribute(LOGININFO_IN_SESSION,loginInfo);
    }


    /**
     *功能描述 将验证码信息保存到session中
     * @author 十一
     * @param vo
     * @return void
     * @date 2019-06-13 21:38
     */
    public static void setVerifyCodeInSession(VerifyCodeVo vo){
        getSession().setAttribute(VERIFYCODE_IN_SESSION,vo);
    }


    /**
     *
     * 功能描述: 获取session中的用户信息
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    public static LoginInfo getCurrentUser(){
        return (LoginInfo)getSession().getAttribute(LOGININFO_IN_SESSION);
    }


    /**
     *
     * 功能描述: 获取session中的验证码信息
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    public static VerifyCodeVo getVerifyCodeInSession(){
        return (VerifyCodeVo)getSession().getAttribute(VERIFYCODE_IN_SESSION);
    }



}
