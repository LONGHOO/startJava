package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.qo.PageResult;
import cn.wolfcode.rbac.qo.QueryObject;
import cn.wolfcode.rbac.domain.Department;

public interface IDepartmentService {
    void delete(Long id);

    Department get(Long id);

    void saveOrUpdate(Department dept);

    PageResult<Department> list(QueryObject qo);
}
