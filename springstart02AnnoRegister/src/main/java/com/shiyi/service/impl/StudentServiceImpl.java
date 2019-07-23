package com.shiyi.service.impl;

import com.shiyi.dao.IStudentDAO;
import com.shiyi.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 十一
 * @Date: 2019-04-14 19:22
 * @Descrption
 **/
@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentDAO studentDAO;

    @Override
    public void save(String username, String password) {
        studentDAO.insert(username,password);
    }
}
