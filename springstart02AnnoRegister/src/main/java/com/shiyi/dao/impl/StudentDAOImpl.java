package com.shiyi.dao.impl;

import com.shiyi.dao.IStudentDAO;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: 十一
 * @Date: 2019-04-14 19:18
 * @Descrption
 **/
@Repository
public class StudentDAOImpl implements IStudentDAO {

    @Autowired
    private DataSource dataSource;

    @Override
    public void insert(String username,String password){
        try {
            @Cleanup
            Connection conn = dataSource.getConnection();
            @Cleanup
            PreparedStatement ps = conn.prepareStatement("insert into student(username,password) values(?,?)");
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
