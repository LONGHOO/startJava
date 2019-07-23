package com.shiyi.ioc;

import com.shiyi.spring.Cat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 十一
 * @Date: 2019-04-13 18:44
 * @Descrption
 **/
public class ScopeTest {


    @Test
    public void testCreateBeanWithFactoryBean(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        System.out.println("__________________");
        Dog dog = ctx.getBean("dog", Dog.class);
        dog.work();
    }


    @Test
    public void testDogSingleTon(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        System.out.println("__________________");
        Dog dog = ctx.getBean("dog2", Dog.class);
        dog.work();
    }


}
