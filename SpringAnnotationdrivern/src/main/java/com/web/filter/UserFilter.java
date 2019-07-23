package com.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: 十一
 * @Date: 2019-04-27 10:31
 * @Descrption
 **/
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("suerfilter init method be called");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("suerservlet dofiler be called");
        System.out.println("hello girel");
        chain.doFilter(request,response);

    }

    @Override
    public void destroy() {
        System.out.println("userfilter destory method be claled");
    }
}
