package com.shiyi.domain;

import java.lang.reflect.Proxy;

/**
 * @Author: 十一
 * @Date: 2019-05-02 21:34
 * @Descrption
 **/
public class TestMethod {


    public static void main(String[] args){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Person person = new Person();
        ISave saveServcie = (ISave) Proxy.newProxyInstance(person.getClass().getClassLoader(),
                Person.class.getInterfaces(),new ProxyPerson(person));
        saveServcie.save();
    }
}
