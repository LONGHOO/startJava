package com.shiyi;

import com.shiyi.mapper.AccountMapper;
import com.shiyi.service.AccountServiceImpl;
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
@ContextConfiguration("classpath:applicationContext.xml")
public class TestMapper {

    @Autowired
    private AccountMapper mapper;

    @Test
    public void testTransfer(){
        mapper.addBalance(1L,new BigDecimal(20));
    }
}
