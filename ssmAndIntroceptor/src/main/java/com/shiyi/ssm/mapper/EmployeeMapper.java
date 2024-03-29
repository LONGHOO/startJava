package com.shiyi.ssm.mapper;

import com.shiyi.ssm.domain.Employee;
import java.util.List;

public interface EmployeeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
}