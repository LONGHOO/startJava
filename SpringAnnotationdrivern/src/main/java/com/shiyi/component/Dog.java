package com.shiyi.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author: 十一
 * @Date: 2019-04-26 21:51
 * @Descrption
 **/
public class Dog implements BeanPostProcessor {

    public Dog() {
        System.out.println("dog constructor ...");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("post processBeforeInitializaition" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization" + beanName);
        return bean;
    }
}
