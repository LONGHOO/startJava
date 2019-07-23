package com.shiyi.test;

import com.shiyi.mybatis.domain.Department;
import com.shiyi.mybatis.domain.Employee;
import com.shiyi.mybatis.mapper.DepartMentMapper;
import com.shiyi.mybatis.mapper.EmployeeMapper;
import com.shiyi.util.MybatisHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @Author: 十一
 * @Date: 2019-04-10 18:39
 * @Descrption
 **/
public class TestManyToOne {

    /**
     *
     * 功能描述:分步查询方式
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    @Test
    public void testQueryByEmployeeId(){
        SqlSession session = MybatisHelper.getSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee emp = mapper.getEmployeeById(1L);
        System.out.println(emp);
        session.close();
    }

    @Test
    public void testQueryByMultipulSql(){
        SqlSession session = MybatisHelper.getSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee employeeByIdWhitMultiSql = mapper.getEmployeeByIdWhitMultiSql(1L);
        System.out.println(employeeByIdWhitMultiSql);
        session.close();
    }
    @Test
    public void testOneToMany(){
        SqlSession session = MybatisHelper.getSession();
        DepartMentMapper mapper = session.getMapper(DepartMentMapper.class);
        Department department = mapper.queryById(10L);
        System.out.println(department);
        session.close();
    }

    @Test
    public void testOneToManySave() throws IOException {
        SqlSessionFactory factory  = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession session = factory.openSession();
        DepartMentMapper deptMapper = session.getMapper(DepartMentMapper.class);
        Department department = new Department(null,"信息化",null);
        Employee emp = new Employee(null, "浩二", BigDecimal.TEN,"0994",null,null);
        deptMapper.save(department);
        emp.setDeptId(department.getId());
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        employeeMapper.save(emp);
        session.commit();
        session.close();
    }
}
