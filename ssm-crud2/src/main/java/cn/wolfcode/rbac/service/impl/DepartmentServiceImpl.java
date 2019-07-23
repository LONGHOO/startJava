package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.qo.QueryObject;
import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.mapper.DepartmentMapper;
import cn.wolfcode.rbac.qo.PageResult;
import cn.wolfcode.rbac.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void delete(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
    }


    @Override
    public Department get(Long id) {

        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(Department dept) {
        if (dept.getId() != null) {
            departmentMapper.updateByPrimaryKey(dept);
        } else {
            departmentMapper.insert(dept);
        }

    }

    @Override
    public PageResult<Department> list(QueryObject qo) {
        Integer rows = departmentMapper.queryForCount(qo);
        return new PageResult<Department>(qo.getPageSize(), qo.getCurrentPage()
                , rows, rows == 0 ? Collections.emptyList() : departmentMapper.queryForList(qo));
    }
}
