package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.mapper.DepartmentMapper;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IDepartmentService;
import cn.wolfcode.util.PageResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);

    }

    @Override
    public int insert(Department record) {
        return mapper.insert(record);
    }

    @Override
    public Department selectByPrimaryKey(Long id) {
        Department department = mapper.selectByPrimaryKey(id);
        return department;
    }

    @Override
    public List<Department> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Department record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
       return PageResultHelper.getPageResult(mapper,qo);
    }

    @Override
    public void saveOrUpdate(Department department) {
        if (department.getId() != null) {
            mapper.updateByPrimaryKey(department);
        } else {
            mapper.insert(department);
        }
    }
}
