package com.shiyi.servletContainerInit;

import com.shiyi.service.IUserService;
import com.web.filter.UserFilter;
import com.web.lister.MyListerner;
import com.web.servlet.UserServlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * @Author: 十一
 * @Date: 2019-04-27 10:23
 * @Descrption
 **/


@HandlesTypes({IUserService.class})
public class MyServletContainerInitilazition implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
//        System.out.println("ininilazitiong be called --------------");
//        for (Class<?> clazz : set) {
//            System.out.println(clazz.getName());
//        }
//        ServletRegistration.Dynamic userServelt = servletContext.addServlet("userServelt", UserServlet.class);
//        userServelt.addMapping("/userServlet");
//        servletContext.addListener(MyListerner.class);
//        FilterRegistration.Dynamic userFilter = servletContext.addFilter("userFilter", UserFilter.class);
//        userFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");
    }
}
