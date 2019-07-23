package com.shiyi.springbootmybatisdemo.start.mapper;

import com.shiyi.springbootmybatisdemo.start.User;

/**
 * @Author: 十一
 * @Date: 2019-03-11 17:38
 * @Descrption
 **/
public interface UserMapper {

    public User selectUser(long id);

    public void insertUser(User user);
}
