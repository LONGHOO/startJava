package com.shiyi.demo001.config;

import com.shiyi.demo001.domain.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: 十一
 * @Date: 2019-06-14 14:22
 * @Descrption
 **/
@Configuration
@PropertySource("classpath:message.properties")
//@EnableConfigurationProperties(Message.class)
public class MessageConfig {

    @Bean
    public Message message(){
        return new Message();
    }
}
