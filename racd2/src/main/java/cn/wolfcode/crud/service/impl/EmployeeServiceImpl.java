package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.mapper.EmployeeMapper;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IEmployeeService;
import cn.wolfcode.util.EmpContext;
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
        return  employeeMapper.deleteByPrimaryKey(id);
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
        int rows = employeeMapper.queryForCount(qo);
        if(rows==0){
            return new PageResult(qo.getCurrentPage(),qo.getPageSize(),0, Collections.EMPTY_LIST);
        }
        List<Employee> list = employeeMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),rows,list);
    }

    @Override
    public void saveOrUpdate(Employee employee,Long[] ids) {
        if(employee.getId()==null){
            if(ids!=null){
                for (Long id : ids) {
                    employeeMapper.insertRelationship(employee.getId(),id);
                }
        }
            employeeMapper.insert(employee);
        }else{
           if(employee.getId()!=null){
               employeeMapper.deleteRelationship(employee.getId());
           }
            employeeMapper.updateByPrimaryKey(employee);
            if(ids!=null){
                for (Long id : ids) {
                    employeeMapper.insertRelationship(employee.getId(),id);
                }
            }
        }
    }

    @Override
    public Employee checkUserByUsernameAndPassword(String username, String password) {
        Employee emp = employeeMapper.getEmpByUserNameAndPassword(username, password);
        if(emp == null){
            throw new RuntimeException("您的用户名或密码不正确！请重试");
        }
        EmpContext.setEmployeeInSession(emp);
        Set<String> permissions = employeeMapper.queryPermissionByEmpId(emp.getId());
        //把当前的用户信息保存到session中
        EmpContext.setPermissionsInSession(permissions);
        return emp;
    }
}
