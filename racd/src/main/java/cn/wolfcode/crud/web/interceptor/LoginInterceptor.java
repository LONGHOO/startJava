package cn.wolfcode.crud.web.interceptor;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.util.EmpContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 十一
 * @Date: 2019-05-03 19:29
 * @Descrption
 **/
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee empl = EmpContext.getEmployeeInSession();
        if(empl == null){
            response.sendRedirect("/login.jsp");
            return false;
        }
        return true;
    }
}
