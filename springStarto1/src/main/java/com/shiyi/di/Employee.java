package com.shiyi.di;

import java.math.BigDecimal;

/**
 * @Author: 十一
 * @Date: 2019-04-13 19:38
 * @Descrption
 **/
public class Employee {

    private String name;
    private Integer age;
    private BigDecimal salary;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
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
}
