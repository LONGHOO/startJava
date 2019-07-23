package com.shiyi.anno;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author: 十一
 * @Date: 2019-04-14 18:59
 * @Descrption
 **/
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MydataSource {



    public MydataSource(){
        System.out.println("constructor be called");
    }

    public void getConnection(){
        System.out.println("getConnection");
    }

    @PostConstruct
    public void init(){
        System.out.println("init method be called!");
    }

    @PreDestroy
    public void destory(){
        System.out.println("destory method be called!");
    }

}
