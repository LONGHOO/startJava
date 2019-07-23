package cn.wolfcode.crud.mapper;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.query.QueryObject;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    int selectForCount(QueryObject qo);

    List<Department> selectForList(QueryObject qo);
}