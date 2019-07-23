package com.shiyi.mybatis.mapper;

import com.shiyi.mybatis.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-09 21:05
 * @Descrption
 **/
public interface EmployeeMapper {

    Employee getEmployeeById(@Param("id") Long id);

    List<Employee> queryAll();

    Employee getEmployeeByIdWhitMultiSql(@Param("id") Long id);

    List<Employee> queryListByDepartmentId(@Param("id") Long id);


    void save(Employee emp);
}
