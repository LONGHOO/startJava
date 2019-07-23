package com.shiyi.domain;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: 十一
 * @Date: 2019-05-02 21:32
 * @Descrption
 **/
public class ProxyPerson implements InvocationHandler {

    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    public ProxyPerson(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = null;
        try{
            result = method.invoke(person,args);
        }catch (Exception e){
            throw e;
        }finally {
            System.out.println("after");
        }
        return result;
    }
}
