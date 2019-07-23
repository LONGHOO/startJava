package cn.wolfcode.trip.base.util;

import cn.wolfcode.trip.base.domain.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author: 十一
 * @Date: 2019-05-04 15:00
 * @Descrption
 **/
public class UserContext {

    private UserContext(){}

    public static final String USER_IN_SESSION =  "USER_IN_SESSION";

    /**
     *
     * 功能描述:
     * @param:  获取到当前会话的session
     * @return:
     * @auther: 十一
     * @date:
     */
    public static HttpSession getSession(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes req = (ServletRequestAttributes)requestAttributes;
        return req.getRequest().getSession();
    }

    /**
     *
     * 功能描述:将当前用户的信息设置到Session中
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    public static void setUserInSession(User user){
        getSession().setAttribute(USER_IN_SESSION,user);
    }


    /**
     *
     * 功能描述: 获取session中的用户信息
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    public static User getUserInSession(){
        return (User)getSession().getAttribute(USER_IN_SESSION);
    }



}
