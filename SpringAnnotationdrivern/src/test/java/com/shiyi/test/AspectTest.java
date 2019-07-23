package com.shiyi.test;

import com.shiyi.config.AspectConfig;
import com.shiyi.service.IUserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: 十一
 * @Date: 2019-04-29 15:02
 * @Descrption
 **/
public class AspectTest {

    @Test
    public void userServiceLogAspectTest(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AspectConfig.class);
        IUserService service = (IUserService) ctx.getBean("service");
        service.save();
        String[] names = ctx.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
