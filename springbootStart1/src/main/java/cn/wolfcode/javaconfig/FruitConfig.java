package cn.wolfcode.javaconfig;

import org.springframework.context.annotation.*;

/**
 * @Author: 十一
 * @Date: 2019-06-09 19:06
 * @Descrption
 **/
@PropertySource(value = {"classpath:/db.properties"})
@Configuration
@ComponentScan("cn.wolfcode.javaconfig")
//@Import(AppleConfig.class)

@ImportResource("classpath:applicationContext.xml")
public class FruitConfig {

    @Bean
    public Fruit fruit(Apple apple){
        Fruit fruit = new Fruit();
        fruit.setApple(apple);
        return fruit;
    }

    @Bean
    public DataSource dataSource(){
        return new DataSource();
    }
}
