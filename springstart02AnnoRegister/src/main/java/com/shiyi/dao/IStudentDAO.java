package com.shiyi.dao;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: 十一
 * @Date: 2019-04-14 19:14
 * @Descrption
 **/
public interface IStudentDAO {

    public void insert(String username,String password);

}
