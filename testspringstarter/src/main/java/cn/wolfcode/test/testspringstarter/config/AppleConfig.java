package cn.wolfcode.test.testspringstarter.config;

import cn.wolfcode.test.testspringstarter.domain.Apple;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 十一
 * @Date: 2019-06-11 17:36
 * @Descrption
 **/
@Configuration
public class AppleConfig {

    @Bean
    public Apple apple(){
        return new Apple();
    }
}
