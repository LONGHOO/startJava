package com.shiyi.mybatis.mapper;

import com.shiyi.mybatis.domain.Student;
import com.shiyi.mybatis.domain.Teacher;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-09 21:05
 * @Descrption
 **/
public interface StudentMapper{

    public static Long ID = 1L;

    //保存自己的id
    void insert(Student student);

    //根据id删除
    void deleteById(@Param("id") Long id);

    Student getStudentById(@Param("id")Long id);

    List<Student> getStudentSingleById(@Param("id")Long id);
}
