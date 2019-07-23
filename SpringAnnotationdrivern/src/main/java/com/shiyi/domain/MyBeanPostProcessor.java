package com.shiyi.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author: 十一
 * @Date: 2019-04-29 19:12
 * @Descrption
 **/
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("-----postProcessBeforeInitlization....." + beanName + "-----" + bean.getClass().getName());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("----postProcessAfterInitialization...." + beanName);
        return bean;
    }
}
