package com.shiyi.ioc;

import com.shiyi.spring.Cat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 十一
 * @Date: 2019-04-13 18:44
 * @Descrption
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class InitAndDestoryTest {

    @Autowired
    private MyDataSource dataSource;

    @Test
    public void testInitAndDestory(){
        dataSource.getConnection();
    }
}
