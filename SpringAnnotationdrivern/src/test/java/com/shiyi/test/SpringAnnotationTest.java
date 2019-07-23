package com.shiyi.test;

import com.shiyi.component.Car;
import com.shiyi.config.AnnotationConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: 十一
 * @Date: 2019-04-28 18:55
 * @Descrption
 **/
public class SpringAnnotationTest {

    @Test
    public void testCar(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        Car car = (Car) ctx.getBean("car");
        System.out.println(car);
    }
}
