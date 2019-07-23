package cn.wolfcode.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 十一
 * @Date: 2019-06-11 15:16
 * @Descrption
 **/
@Configuration
//web应用程序有效
@ConditionalOnWebApplication
@EnableConfigurationProperties(HelloProperties.class)
public class HelloConfig {

    @Bean
    public HelloService helloService(HelloProperties helloProperties){
        HelloService helloService = new HelloService();
        helloService.setHelloProperties(helloProperties);
        return helloService;
    }
}
