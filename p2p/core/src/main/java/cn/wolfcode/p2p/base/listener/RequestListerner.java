package cn.wolfcode.p2p.base.listener;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.util.UserContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 十一
 * @Date: 2019-06-15 21:28
 * @Descrption
 **/
@Component
public class RequestListerner extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod) handler;
            //判断方法上是否标注了LoginRequird
            LoginRequird loginRequird = method.getMethodAnnotation(LoginRequird.class);
            //标注了LoginRequird注解表示需要登陆
            if(loginRequird != null){
                //如果没有登陆则跳转到登陆页
                if(null == UserContext.getCurrentUser()){
                    response.sendRedirect("/login.html");
                    return false;
                }
            }
        }
        return true;
    }
}
