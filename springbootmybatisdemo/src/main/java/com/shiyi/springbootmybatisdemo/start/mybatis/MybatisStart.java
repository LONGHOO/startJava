package com.shiyi.springbootmybatisdemo.start.mybatis;

import com.shiyi.springbootmybatisdemo.start.User;
import com.shiyi.springbootmybatisdemo.start.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * @Author: 十一
 * @Date: 2019-03-11 17:13
 * @Descrption
 **/
public class MybatisStart {

    private static InputStream inputStream = null;
    private static SqlSessionFactory sqlSessionFactory = null;
    static {
        try{
            String resource = "mybatis.xml";
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }


    @Test
    public void selectUser(){
        SqlSession session = MybatisStart.getSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser(1);
            System.out.println(user);

        } finally {
            session.close();
        }
    }

    @Test
    public void testInsert(){
        User user = new User(null,"corroer",33);
        SqlSession session = MybatisStart.getSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.insertUser(user);
            System.out.println(user);
        } finally {
            session.close();
        }
    }

}
