package com.shiyi.persontest;

import com.shiyi.ioc.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 十一
 * @Date: 2019-04-13 12:42
 * @Descrption
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PersonTest {

    @Autowired
    private Person person;

    @Test
    public void testPersonByAutoWired() throws Exception{
        person.work();
    }

    @Test
    public void testPersonByXML(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //根据名字获取
        Person person = (Person) ctx.getBean("person");
        person.work();
        //根据类型获取
        Person person1 = ctx.getBean(Person.class);
        person1.work();
        //通过名称和类型获取
        Person person2 = ctx.getBean("person", Person.class);
        person2.work();

    }


}
