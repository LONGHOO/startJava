package com.shiyi.test;


import com.shiyi.mybatis.domain.Employee;
import com.shiyi.mybatis.mapper.EmployeeMapper;
import com.shiyi.util.MybatisHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-09 19:55
 * @Descrption
 **/
public class MybatisTest {


    @Test
    public void testMinSalary() {
        SqlSession session = MybatisHelper.getSession();
        EmployeeMapper empMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> employees = empMapper.queryByMinSalary(new BigDecimal(900));
        for (Employee employee : employees) {
            System.out.println(employee);

        }
    }

    @Test
    public void testMinSalaryAndMaxSalary() {
        SqlSession session = MybatisHelper.getSession();
        EmployeeMapper empMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> employees = empMapper.qeuryByMinSalaryAndMaxSalary(new BigDecimal(500),
                new BigDecimal(1000));
        for (Employee employee : employees) {
            System.out.println(employee);

        }
    }

    @Test
    public void testQueryByDept() {
        SqlSession session = MybatisHelper.getSession();
        EmployeeMapper empMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> employees = empMapper.queryByDeptId(10L);
        for (Employee employee : employees) {
            System.out.println(employee);

        }
    }
}
