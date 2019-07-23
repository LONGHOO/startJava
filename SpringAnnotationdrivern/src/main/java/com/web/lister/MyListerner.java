package com.web.lister;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author: 十一
 * @Date: 2019-04-27 10:33
 * @Descrption
 **/
public class MyListerner implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("this is MyListner");
    }
}
