package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.mapper.EmployeeMapper;
import cn.wolfcode.rbac.qo.PageResult;
import cn.wolfcode.rbac.qo.QueryObject;
import cn.wolfcode.rbac.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void delete(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
    }


    @Override
    public Employee get(Long id) {

        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(Employee dept) {
        if (dept.getId() != null) {
            employeeMapper.updateByPrimaryKey(dept);
        } else {
            employeeMapper.insert(dept);
        }

    }

    @Override
    public PageResult<Employee> queryOfPageResult(QueryObject qo) {
        Integer rows = employeeMapper.queryForCount(qo);
        return new PageResult<Employee>(qo.getPageSize(), qo.getCurrentPage()
                , rows, rows == 0 ? Collections.emptyList() : employeeMapper.queryForList(qo));
    }
}
