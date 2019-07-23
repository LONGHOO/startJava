package com.shiyi.demo001;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: 十一
 * @Date: 2019-06-14 23:39
 * @Descrption
 **/
@Component
public class Conponent implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName+">>>>>>>>>>>>>>>");
        return bean;
    }
}
