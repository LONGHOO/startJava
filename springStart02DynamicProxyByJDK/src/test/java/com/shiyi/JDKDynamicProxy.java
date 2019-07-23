package com.shiyi;

import com.shiyi.handler.TransactionHandler;
import com.shiyi.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Proxy;

/**
 * @Author: 十一
 * @Date: 2019-04-13 20:31
 * @Descrption
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JDKDynamicProxy {

    @Autowired
    private TransactionHandler tx;


    @Test
    public void testInsert() throws Exception {

        IEmployeeService service = (IEmployeeService) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                tx.getService().getClass().getInterfaces(),
                tx);
        service.save();
    }



}
