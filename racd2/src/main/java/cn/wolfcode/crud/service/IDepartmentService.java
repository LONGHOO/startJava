package cn.wolfcode.crud.service;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;

import java.util.List;

public interface IDepartmentService  {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    PageResult query(QueryObject qo);

    void  saveOrUpdate(Department department);
}
