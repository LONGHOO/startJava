package com.shiyi.ioc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 十一
 * @Date: 2019-04-13 18:44
 * @Descrption
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CreateBeanTest {


    @Test
    public void testCreateBeanWithNoargConstructor(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Cat1 cat1 = ctx.getBean("cat1", Cat1.class);
        System.out.println(cat1);
    }

    @Test
    public void testCreateBeanWithStaticFactory(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Cat2 cat2 = ctx.getBean(Cat2.class);
        System.out.println(cat2);
        //System.out.println(cat2);
    }

    @Test
    public void testCreateBeanWithInstanceFactory() throws Exception{
      //  System.out.println(cat3);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Cat3 cat3 = ctx.getBean(Cat3.class);
        System.out.println(cat3);
    }

    @Test
    public void testCreateBeanWithFactoryBean(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Cat4 cat4 = ctx.getBean(Cat4.class);
        System.out.println(cat4);
    }


}
