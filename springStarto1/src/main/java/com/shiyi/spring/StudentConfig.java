package com.shiyi.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 十一
 * @Date: 2019-04-13 12:55
 * @Descrption
 **/
@Configuration
public class StudentConfig {



    @Bean
    @Conditional(TeacherExistConditon.class)
    public Teacher teacher(){
        Teacher teacher = new Teacher();
        teacher.setName("玛丽亚");
        return teacher;
    }

    @Bean
    public Student student3(){
        Student student = new Student();
        student.setName("杨过");
        return student;
    }
}
