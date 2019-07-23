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

    List<Employee> queryByMinSalary(@Param("salary") BigDecimal minSalary);
    List<Employee> qeuryByMinSalaryAndMaxSalary(@Param("minSalary") BigDecimal minSalary,
                                                @Param("maxSalary") BigDecimal maxSalary);
    List<Employee> queryByDeptId(@Param("deptId") Long dept);
    List<Employee> queryByStep(@Param("id") Long id);

    List<Employee> queryAll();



}
