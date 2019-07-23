package com.shiyi.service.impl;

import com.shiyi.domain.Department;
import com.shiyi.mapper.DepartmentMapper;
import com.shiyi.query.QueryObject;
import com.shiyi.service.IDepartmentService;
import com.shiyi.service.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-20 17:39
 * @Descrption
 **/
@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Department record) {
        return departmentMapper.insert(record);
    }

    @Override
    public Department selectByPrimaryKey(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Department record) {
        return departmentMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult getPageResultByQueryObject(QueryObject queryObject) {
        int rows = departmentMapper.queryRowsByQueryObject(queryObject);
        if(rows == 0){
            return new PageResult(queryObject.getCurrentPage(),queryObject.getPageSize(), Collections.emptyList(),rows);
        }
        queryObject.verifyCurrentPage(rows);
        List<Department> departments = departmentMapper.queryDepartmentsByQueryObeject(queryObject);
        return new PageResult(queryObject.getCurrentPage(),queryObject.getPageSize(),departments,rows);
    }


}
