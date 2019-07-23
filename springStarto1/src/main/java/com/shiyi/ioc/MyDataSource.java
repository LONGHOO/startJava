package com.shiyi.ioc;

import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @Author: 十一
 * @Date: 2019-04-13 18:42
 * @Descrption
 **/
@Component
public class MyDataSource {

    public void init(){
        System.out.println("init");
    }

    public void getConnection(){
        System.out.println("getConnection");
    }

    public void destory(){
        System.out.println("destory");
    }

}
