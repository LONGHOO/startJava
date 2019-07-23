package com.shiyi.mybatis.mapper;

import com.shiyi.mybatis.domain.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-09 21:42
 * @Descrption
 **/
public interface DepartMentMapper {

    void insert(Department dept);

    Department queryById(@Param("id") Long id);

    List<Department> queryAll();
}
