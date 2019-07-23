package com.shiyi.di;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.PreparedStatement;

/**
 * @Author: 十一
 * @Date: 2019-04-13 19:56
 * @Descrption
 **/
public class EmployeeDAO {

    private DruidDataSource dataSource;

    public void setDataSource(DruidDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(String userName,String password) throws Exception{
        DruidPooledConnection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into student(username,password) values(?,?)");
        ps.setString(1,userName);
        ps.setString(2,password);
        ps.executeUpdate();

    }
}
