package com.shiyi.mp.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 十一
 * @Date: 2019-04-12 17:06
 * @Descrption
 **/
@Configuration
public class StudentConfig {

    @Bean
    public Student student(){
        Student student = new Student();
        student.setName("杜敏");
        student.setAge(23);
        return student;
    }
}
