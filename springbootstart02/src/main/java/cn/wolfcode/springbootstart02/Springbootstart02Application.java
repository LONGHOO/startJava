package cn.wolfcode.springbootstart02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@MapperScan("cn.wolfcode.springbootstart02.mapper")
public class Springbootstart02Application {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        SpringApplication.run(Springbootstart02Application.class, args);
    }

}
