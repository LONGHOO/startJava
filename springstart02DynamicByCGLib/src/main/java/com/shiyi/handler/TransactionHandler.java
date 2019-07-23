package com.shiyi.handler;

import com.shiyi.tx.TransactionManager;
import org.springframework.cglib.proxy.InvocationHandler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @Author: 十一
 * @Date: 2019-04-14 21:00
 * @Descrption
 **/

public class TransactionHandler implements InvocationHandler {
    //需要实现的接口是CGlib下的InvocationHandler

    private Object service;



    private TransactionManager tx;

    public void setService(Object service) {
        this.service = service;
    }

    public void setTx(TransactionManager tx) {
        this.tx = tx;
    }

    public Object getService() {
        return service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try{
            tx.begin();
            BeanInfo beanInfo = Introspector.getBeanInfo((Class<?>) proxy);
            method.invoke(service,args);
            tx.commit();
        }catch (Exception e){
            tx.rollBack();
        }
        return null;
    }
}
