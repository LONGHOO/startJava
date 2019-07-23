package com.shiyi.config;

import com.shiyi.component.Cat;
import com.shiyi.domain.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 十一
 * @Date: 2019-04-29 19:15
 * @Descrption
 **/
@Configuration
public class BeanPostProcessorConfig {

    @Bean
    public MyBeanPostProcessor student(){
        return new MyBeanPostProcessor();
    }

    @Bean
    public Cat cat(){
        return new Cat();
    }
}
