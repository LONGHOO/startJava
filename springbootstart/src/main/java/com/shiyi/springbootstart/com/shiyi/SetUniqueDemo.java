package com.shiyi.springbootstart.com.shiyi;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: 十一
 * @Date: 2019-03-08 21:29
 * @Descrption
 **/
public class SetUniqueDemo {

    @Test
    public void testSet() throws Exception{
//        String str = "hello";
//        String str1 = "world";
//        String str2 = "hello";
//        Set<String> set = new HashSet<String>();
//        set.add(str);
//        set.add(str2);
//        set.add(str1);
//        for (String s : set) {
//            System.out.println(s);
//        }

        person person2 = new person("李四", 33);
        person person3 = new person("张三", 12);
        Set<person> set = new HashSet<person>();
        Set set1  = new HashSet();
        Class<? extends Set> clazz1 = set.getClass();
        Class<? extends Set> clazz2 = set1.getClass();
        set.add(new person("张三", 12));
//        set.add("hello world");
        Method add = clazz1.getMethod("add", Object.class);
        add.invoke(set,"hello world");
        for (Object obj : set) {
            System.out.println(obj);

        }
//        set.add(person1);
//        set.add(person2);
//        set.add(person3);
//        for (person person : set) {
//            System.out.println(person.toString());
//        }
    }
}

class person{

    private String name;
    private Integer age;

    @Override
    public String toString(){
        return "name="+this.name + "age="+this.age;
    }
    public person(String name,int age){
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() * this.age.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof person) {
            person person = (person)obj;
            if(person.name.equals(this.name) && person.age.equals(this.age)){
                return true;
            }
        }
        return false;
    }
}
