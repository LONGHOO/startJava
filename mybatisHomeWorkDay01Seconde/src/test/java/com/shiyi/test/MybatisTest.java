package com.shiyi.test;

import com.shiyi.mybatis.domain.User;
import com.shiyi.mybatis.mapper.UserMapper;
import com.shiyi.util.MybatisHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-09 19:55
 * @Descrption
 **/
public class MybatisTest {

   @Test
    public void testLogin(){
       SqlSession session = MybatisHelper.getSession();
       User user = new User(null,"张三","123",null);
       UserMapper userMapper = session.getMapper(UserMapper.class);
       User result = userMapper.login(user);
       if(result != null){
           System.out.println("登陆成功");
           return;
       }
       System.out.println("登陆失败");
   }
}
