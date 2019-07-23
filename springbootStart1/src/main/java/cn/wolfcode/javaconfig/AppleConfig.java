package cn.wolfcode.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 十一
 * @Date: 2019-06-09 19:22
 * @Descrption
 **/
@Configuration
public class AppleConfig {

    @Bean(initMethod = "init",destroyMethod = "destoryMethod")
    public Apple apple(){
        return new Apple();
    }
}
