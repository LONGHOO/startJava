package com.shiyi.test;

import com.shiyi.config.BeanPostProcessorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 十一
 * @Date: 2019-04-29 19:16
 * @Descrption
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BeanPostProcessTest {

    @Test
    public void testStudent(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);
        String[] names= ctx.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);

        }

    }
}
