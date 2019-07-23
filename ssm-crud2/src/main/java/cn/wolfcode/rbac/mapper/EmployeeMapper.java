package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.qo.QueryObject;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Employee record);

    Integer queryForCount(QueryObject qo);

    List<Employee> queryForList(QueryObject qo);
}