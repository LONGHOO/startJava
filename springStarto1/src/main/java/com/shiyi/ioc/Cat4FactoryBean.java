package com.shiyi.ioc;

import com.shiyi.spring.Cat;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: 十一
 * @Date: 2019-04-13 18:42
 * @Descrption
 **/
public class Cat4FactoryBean implements FactoryBean<Cat4> {


    @Override
    public Cat4 getObject() throws Exception {
        return new Cat4();
    }

    @Override
    public Class<?> getObjectType() {
        return Cat4.class;
    }
}
