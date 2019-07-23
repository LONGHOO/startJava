package cn.wolfcode.p2p;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: 十一
 * @Date: 2019-06-12 13:16
 * @Descrption
 **/
@Configuration
@MapperScan(basePackages = {"cn.wolfcode.p2p.base.mapper","cn.wolfcode.p2p.bussniss.mapper"})
@PropertySource("classpath:message.properties")
public class AppConfig {
}
