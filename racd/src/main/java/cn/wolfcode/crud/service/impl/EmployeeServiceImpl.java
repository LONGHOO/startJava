package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.mapper.EmployeeMapper;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IEmployeeService;
import cn.wolfcode.util.EmpContext;
import cn.wolfcode.util.PageResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    @Override
    public List<Employee> selectAll() {
        List<Employee> employees = employeeMapper.selectAll();


        return employees;
    }

    @Override
    public PageResult query(QueryObject qo) {
        return PageResultHelper.getPageResult(employeeMapper, qo);
    }

    @Override
    public void saveOrUpdate(Employee employee, Long[] ids) {
        if (employee.getId() != null) {
            employeeMapper.updateByPrimaryKey(employee);
        } else {
            employeeMapper.insert(employee);
        }
        employeeMapper.deleteRelationship(employee.getId());
        employeeMapper.insertRelationship(employee.getId(), ids);
    }

    @Override
    public void checkUserByUsernameAndPassword(String username, String password) {
        Employee emp = employeeMapper.getEmpByUserNameAndPassword(username, password);
        if (emp == null) {
            throw new RuntimeException("您的用户名或密码错误，请重试！");
        }
        //将用户信息保存到Session中
        EmpContext.setEmployeeInSession(emp);
        //查询用户的权限信息
        Set<String> permissions = employeeMapper.queryPermissionByEmpId(emp.getId());
        EmpContext.setPermissionsInSession(permissions);
    }
}
