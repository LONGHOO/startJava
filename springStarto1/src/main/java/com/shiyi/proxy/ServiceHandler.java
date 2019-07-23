package com.shiyi.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: 十一
 * @Date: 2019-04-14 14:09
 * @Descrption
 **/
public class ServiceHandler implements InvocationHandler {

    private ServiceImpl serviceImpl;

    public void setService(ServiceImpl service){
        this.serviceImpl = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before");
        method.invoke(serviceImpl,args);
        System.out.println("after");
        return null;
    }

}
