package com.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 十一
 * @Date: 2019-04-27 10:30
 * @Descrption
 **/
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("this is httpServlte");
        resp.getWriter().println("this is userservlet's response.....");
        resp.flushBuffer();

    }
}
