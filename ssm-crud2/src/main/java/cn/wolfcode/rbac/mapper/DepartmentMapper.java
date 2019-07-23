package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.qo.QueryObject;
import cn.wolfcode.rbac.domain.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Department dept);

    Integer queryForCount(QueryObject qo);

    List<Department> queryForList(QueryObject qo);

}