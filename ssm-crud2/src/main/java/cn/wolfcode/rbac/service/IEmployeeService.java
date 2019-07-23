package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.qo.PageResult;
import cn.wolfcode.rbac.qo.QueryObject;

public interface IEmployeeService {
    void delete(Long id);

    Employee get(Long id);

    void saveOrUpdate(Employee dept);

    PageResult<Employee> queryOfPageResult(QueryObject qo);
}
