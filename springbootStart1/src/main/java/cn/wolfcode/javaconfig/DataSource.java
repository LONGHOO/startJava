package cn.wolfcode.javaconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author: 十一
 * @Date: 2019-06-09 19:27
 * @Descrption
 **/
@Getter
@Setter
@ToString
public class DataSource {

    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.driverClassName}")
    private String driver;
    @Value("${jdbc.password}")
    private String password;
}
