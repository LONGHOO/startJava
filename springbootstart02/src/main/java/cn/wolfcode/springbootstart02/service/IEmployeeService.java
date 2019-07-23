package cn.wolfcode.springbootstart02.service;

import cn.wolfcode.springbootstart02.domain.Employee;
import cn.wolfcode.springbootstart02.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IEmployeeService {
    void saveOrUpdate(Employee entity);

    void delete(Long id);

    Employee get(Long id);

    PageInfo<Employee> query(QueryObject qo);
}
