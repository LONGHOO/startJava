package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.qo.QueryObject;
import cn.wolfcode.rbac.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceImplTest {
    @Autowired
    private IEmployeeService employeeService;
    @Test
    public void list() {
        employeeService.queryOfPageResult(new QueryObject());
    }
}