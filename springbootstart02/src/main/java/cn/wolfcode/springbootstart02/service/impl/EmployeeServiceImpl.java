package cn.wolfcode.springbootstart02.service.impl;

import cn.wolfcode.springbootstart02.domain.Employee;
import cn.wolfcode.springbootstart02.mapper.EmployeeMapper;
import cn.wolfcode.springbootstart02.query.QueryObject;
import cn.wolfcode.springbootstart02.service.IEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public void saveOrUpdate(Employee entity) {
        if(null == entity.getId()){
            employeeMapper.insert(entity);
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Employee> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Employee> list = employeeMapper.query(qo);
        return new PageInfo<>(list);
    }
}
