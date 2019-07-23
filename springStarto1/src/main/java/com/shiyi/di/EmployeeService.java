package com.shiyi.di;

/**
 * @Author: 十一
 * @Date: 2019-04-13 20:06
 * @Descrption
 **/
public class EmployeeService {

    private EmployeeDAO dao;

    public EmployeeDAO getDao() {
        return dao;
    }

    public void setDao(EmployeeDAO dao) {
        this.dao = dao;
    }

    public void save(String userName,String password) throws Exception {
        dao.insert(userName,password);
    }
}
