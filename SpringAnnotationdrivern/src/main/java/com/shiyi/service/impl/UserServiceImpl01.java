package com.shiyi.service.impl;

import com.shiyi.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @Author: 十一
 * @Date: 2019-04-27 10:28
 * @Descrption
 **/

@Service("service")
public class UserServiceImpl01 implements IUserService {
    @Override
    public void service() {
        System.out.println("service 01");
    }

    @Override
    public String save() {
        System.out.println("userService save method be called ......");
        return "return value";
    }
}
