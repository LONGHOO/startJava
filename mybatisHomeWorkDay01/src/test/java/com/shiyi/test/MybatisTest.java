package com.shiyi.test;

import com.shiyi.mybatis.domain.User;
import com.shiyi.mybatis.mapper.UserMapper;
import com.shiyi.util.MybatisHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-09 19:55
 * @Descrption
 **/
public class MybatisTest {

    @Test
    public void testInsert(){
        SqlSession session = MybatisHelper.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = new User();
        user.setAge(14);
        user.setUsername("张三");
        userMapper.insert(user);
        session.commit();
        session.close();
    }

    @Test
    public void testUpdate(){
        SqlSession session = MybatisHelper.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = new User();
        user.setId(1L);
        user.setAge(14);
        user.setUsername("张三feng");
        userMapper.update(user);
        session.commit();
        session.close();
    }
    @Test
    public void testDelete(){
        SqlSession session = MybatisHelper.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        userMapper.deleteById(1L);
        session.commit();
        session.close();
    }
    @Test
    public void testGetById() throws Exception{
        SqlSession session = MybatisHelper.getSession();
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.getById(1L);
        System.out.println(user);
        session.close();
    }
    @Test
    public void testSelectAll(){
        SqlSession session = MybatisHelper.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> list = userMapper.selectAll();
        for (User user : list) {
            System.out.println(user);
        }
        session.close();
    }
}
