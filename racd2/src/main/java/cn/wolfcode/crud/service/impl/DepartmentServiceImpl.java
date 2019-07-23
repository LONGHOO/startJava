package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.mapper.DepartmentMapper;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IDepartmentService;
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
        mapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(Department record) {
        mapper.insert(record);
        return 0;
    }

    @Override
    public Department selectByPrimaryKey(Long id) {
        Department department = mapper.selectByPrimaryKey(id);
        return department;
    }

    @Override
    public List<Department> selectAll() {
        List<Department> departments = mapper.selectAll();
        return departments;
    }

    @Override
    public int updateByPrimaryKey(Department record) {
         mapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public PageResult query(QueryObject qo) {
        int rows=mapper.queryForCount(qo);
        if(rows==0){
            return new PageResult(qo.getCurrentPage(),qo.getPageSize(),0, Collections.EMPTY_LIST);
        }
        List<Department> list = mapper.queryForList(qo);

        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),rows,list);
    }

    @Override
    public void saveOrUpdate(Department department) {
        if(department.getId()==null){
            mapper.insert(department);
        }else {
           mapper.updateByPrimaryKey(department);
        }
    }
}
