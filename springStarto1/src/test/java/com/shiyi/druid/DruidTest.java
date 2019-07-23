package com.shiyi.druid;

import com.shiyi.di.Employee;
import com.shiyi.di.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 十一
 * @Date: 2019-04-13 19:53
 * @Descrption
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DruidTest {

    @Autowired
    private EmployeeService service;

    @Test
    public void testDruid() throws Exception {
        service.save("zhangsan","66666");

    }


}


