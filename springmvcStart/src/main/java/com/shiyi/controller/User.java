package com.shiyi.controller;

/**
 * @Author: 十一
 * @Date: 2019-04-17 20:25
 * @Descrption
 **/
public class User {

    private String userName;
    private Integer age;
    private Department dept;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Department getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", dept=" + dept +
                '}';
    }

    public void setDept(Department dept) {
        this.dept = dept;

    }
}
