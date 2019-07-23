package cn.wolfcode.crud.web.interceptor;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.util.EmpContext;
import cn.wolfcode.util.RequiredPermission;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @Author: 十一
 * @Date: 2019-05-04 15:08
 * @Descrption
 **/
public class EmpAccessPermissionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee employee = EmpContext.getEmployeeInSession();
        if(employee.isAdmin()){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(!method.isAnnotationPresent(RequiredPermission.class)){
            return true;
        }
        Set<String> set = EmpContext.getPermissionInSession();
        String expression = method.getAnnotation(RequiredPermission.class).value()[1];
        if(set.contains(expression)){
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp").forward(request,response);
        return false;
    }

}
