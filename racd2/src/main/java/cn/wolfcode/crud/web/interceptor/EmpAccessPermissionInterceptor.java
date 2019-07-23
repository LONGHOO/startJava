package cn.wolfcode.crud.web.interceptor;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.util.EmpContext;
import cn.wolfcode.util.RequiredPermission;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @Author: 十一
 * @Date: 2019-05-04 15:08
 * @Descrption
 **/
public class EmpAccessPermissionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取当前登陆的用户
        Employee employee = EmpContext.getEmployeeInSession();
        //判断是否是超级管理员 是则放行
        if(employee.isAdmin()){
            return true;
        }
        //获取当前访问资源的目标方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //判断目标方法上是否带有权限的注解
        if(!handlerMethod.hasMethodAnnotation(RequiredPermission.class)){
            return true;
        }
        //将当前用户的权限与目标权限相比较
        Set<String> permissions = EmpContext.getPermissionInSession();
        String permission = handlerMethod.getMethodAnnotation(RequiredPermission.class).value()[1];
        if(permissions.contains(permission)){
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp").forward(request,response);
        return false;
    }
}
