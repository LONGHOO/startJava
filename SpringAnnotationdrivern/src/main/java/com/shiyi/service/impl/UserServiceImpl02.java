package com.shiyi.service.impl;

import com.shiyi.service.IUserService;

/**
 * @Author: 十一
 * @Date: 2019-04-27 10:29
 * @Descrption
 **/
public class UserServiceImpl02 implements IUserService {
    @Override
    public void service() {
        System.out.println("userservice 02");
    }

    @Override
    public String save() {
        System.out.println("userserice 02 save method be called ....");
        return "return value";
    }
}
