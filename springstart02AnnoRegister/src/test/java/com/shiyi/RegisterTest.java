package com.shiyi;

import com.shiyi.service.IStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 十一
 * @Date: 2019-04-13 20:31
 * @Descrption
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RegisterTest {

    @Autowired
    private IStudentService service;

    @Test
    public void testInsert() throws Exception {
        service.save("红孩儿","longhoo");
    }



}
