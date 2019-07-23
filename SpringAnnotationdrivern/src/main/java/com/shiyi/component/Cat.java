package com.shiyi.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: 十一
 * @Date: 2019-04-26 21:44
 * @Descrption
 **/
public class Cat implements InitializingBean, DisposableBean,  ApplicationContextAware {

    public Cat(){
        System.out.println("cat constructor is running....");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("cat destory method be called");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat init method be called");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
