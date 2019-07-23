package com.shiyi.mapper;

import com.shiyi.domain.Department;
import com.shiyi.query.QueryObject;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    int queryRowsByQueryObject(QueryObject qo);

    List<Department> queryDepartmentsByQueryObeject(QueryObject qo);
}