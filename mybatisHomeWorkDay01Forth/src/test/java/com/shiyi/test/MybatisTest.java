package com.shiyi.test;


import com.shiyi.mybatis.domain.Department;
import com.shiyi.mybatis.domain.Employee;
import com.shiyi.mybatis.mapper.DepartMentMapper;
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
    public void testQueryByStep(){
        SqlSession session = MybatisHelper.getSession();

        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        List<Employee> list = mapper.queryByStep(1L);
        for (Employee employee : list) {
            System.out.println(employee);

        }

    }

    @Test
    public void testDepartment(){
        SqlSession session = MybatisHelper.getSession();
        DepartMentMapper mapper = session.getMapper(DepartMentMapper.class);
        Department department = mapper.queryById(10L);
        System.out.println(department);
    }

    @Test
    public void testStepQueryByHande(){
        SqlSession session = MybatisHelper.getSession();
        EmployeeMapper empMapper = session.getMapper(EmployeeMapper.class);
        DepartMentMapper departMapper = session.getMapper(DepartMentMapper.class);
        Employee employee = empMapper.queryByStep(1L).get(0);
        Department department = departMapper.queryById(employee.getDeptId());
        employee.setDept(department);
        System.out.println(employee);
    }
    @Test
    public void testQureyAll(){
        SqlSession session = MybatisHelper.getSession();
        EmployeeMapper empMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> employees = empMapper.queryAll();
        for (Employee employee : employees) {
            System.out.println(employee);

        }
        session.close();

    }

    @Test
    public void testManyToOneQuery(){
        SqlSession session = MybatisHelper.getSession();
        DepartMentMapper departMentMapper = session.getMapper(DepartMentMapper.class);
        List<Department> list = departMentMapper.queryAll();
        for (Department department : list) {
            System.out.println(department);

        }
        session.close();
    }

    @Test
    public void test() throws Exception{
        Department department = new Department();
        SqlSession session = MybatisHelper.getSession();
        DepartMentMapper departMentMapper = session.getMapper(DepartMentMapper.class);
        departMentMapper.insert(department);
        Employee employees = new Employee();
        employees.setDeptId(department.getId());
    }

}
