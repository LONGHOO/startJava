package com.shiyi.mybatis.mapper;

import com.shiyi.mybatis.domain.Student;
import com.shiyi.mybatis.domain.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-09 21:42
 * @Descrption
 **/
public interface TeacherMapper {


    //保存自己的信息
    void insert(Teacher teacher);
    //保存老师与学生的关系
    void insertRelation(@Param("studentId")Long studentId,@Param("teacherId") Long teacherId);

    void deleteRelation(@Param("studentId")Long studentId,@Param("teacherId")Long teacherId);

    //根据学生id查找老师
    List<Teacher> queryTeacherByStudentId(@Param("studentId")Long id);

}
