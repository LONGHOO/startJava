package com.shiyi.config;

import com.shiyi.component.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: 十一
 * @Date: 2019-04-28 18:57
 * @Descrption
 **/
@PropertySource(value = {"classpath:/car.properties"})
@Configuration
public class AnnotationConfig {

    @Bean
    public Car car(){
        return new Car();
    }
}
