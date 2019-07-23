package com.shiyi.service.impl;

import com.shiyi.service.IEmployeeService;
import org.springframework.stereotype.Service;

/**
 * @Author: 十一
 * @Date: 2019-04-14 19:38
 * @Descrption
 **/
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Override
    public void save() {
        System.out.println("call the save method!");
    }
}
