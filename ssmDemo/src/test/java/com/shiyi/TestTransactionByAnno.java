package com.shiyi;

import com.shiyi.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * @Author: 十一
 * @Date: 2019-04-16 19:30
 * @Descrption
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationAnno.xml")
public class TestTransactionByAnno {

    @Autowired
    private IAccountService service;

    @Test
    public void testTransfer(){
        service.transferAccount(1L,2L,new BigDecimal(20));
    }
}
