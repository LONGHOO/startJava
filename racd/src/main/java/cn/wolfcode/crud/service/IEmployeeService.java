package cn.wolfcode.crud.service;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;

import java.util.List;

public interface IEmployeeService {
    int deleteByPrimaryKey(Long id);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    PageResult query(QueryObject qo);

    void  saveOrUpdate(Employee employee,Long[] ids);

    void checkUserByUsernameAndPassword(String username, String password);
}
