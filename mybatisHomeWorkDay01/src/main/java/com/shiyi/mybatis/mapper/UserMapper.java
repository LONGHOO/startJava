package com.shiyi.mybatis.mapper;

import com.shiyi.mybatis.domain.User;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-09 19:41
 * @Descrption
 **/
public interface UserMapper {

    void deleteById(Long id);

    void update(User user);

    void insert(User user);

    User getById(Long id);

    List<User> selectAll();
}
