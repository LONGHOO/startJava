package com.shiyi;

import com.shiyi.mapper.AccountMapper;
import com.shiyi.service.AccountServiceImpl;
import com.shiyi.service.IAccountService;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 十一
 * @Date: 2019-04-16 19:30
 * @Descrption
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class test {

    @Autowired
    private IAccountService service;

    @Test
    public void testTransfer(){
        service.transferAccount(1L,2L,new BigDecimal(20));
    }

    @Test
    public void testMatch(){
        String strs="hello,world";
        String regex="hello";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(strs);
        if(matcher.find()){
            String s = matcher.replaceAll("*");
            System.out.println(s);
        }
        System.out.println(strs.matches(strs));
    }
}
