package cn.wolfcode.rbac.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginMessage = request.getSession().getAttribute("EMPLOYEE_IN_SESSION");
        if (loginMessage == null) {
            request.getSession().setAttribute("msg", "请先登录!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return false;
        }
        return true;
    }


}
