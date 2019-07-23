package com.shiyi.demo001.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: 十一
 * @Date: 2019-06-14 14:22
 * @Descrption
 **/
@ConfigurationProperties(prefix = "message")
@Getter
@Setter
public class Message{

    private String messageUrl;
    private String appkey;



}
