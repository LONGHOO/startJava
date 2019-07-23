package com.shiyi.test;


import com.shiyi.domain.Person;

import java.lang.reflect.Constructor;

/**
 * @Author: 十一
 * @Date: 2019-05-02 19:39
 * @Descrption
 **/
public class PersonTest {
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
        Person person = new Person();
        personInstace("com.shiyi.domain.Person1");

    }

    public static void personInstace(String fullName) throws ClassNotFoundException, NoSuchMethodException{
        Class<?> clazz = Class.forName(fullName);
        Constructor<?> constructor = clazz.getConstructor(String.class,Integer.class);
        System.out.println(constructor);
    }
}
