package com.shiyi.test;

import com.shiyi.config.BeanConfig;
import com.shiyi.domain.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: 十一
 * @Date: 2019-04-24 20:43
 * @Descrption
 **/
public class SpringTest {

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfig.class);

    @Test
    public void testConfigration() {
        Person person = (Person) ctx.getBean("person");
//        System.out.println(person);
//        String[] names = ctx.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//
//        }
    }

    @Test
    public void testCondition(){

        Object bean = ctx.getBean("dog");
       ctx.close();
    }

}
