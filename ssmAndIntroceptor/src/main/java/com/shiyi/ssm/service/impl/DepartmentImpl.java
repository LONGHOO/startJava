package com.shiyi.ssm.service.impl;

import com.shiyi.ssm.domain.Department;
import com.shiyi.ssm.mapper.DepartmentMapper;
import com.shiyi.ssm.query.QueryObject;
import com.shiyi.ssm.service.IDepartmentService;
import com.shiyi.ssm.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-19 19:48
 * @Descrption
 **/
@Service
public class DepartmentImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(Department record) {
        departmentMapper.insert(record);
        return 0;
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
    public PageResult getPageResult(QueryObject qo) {
        Integer rows = departmentMapper.queryRowsByQueryObejct(qo);
        if(rows == 0){
            return new PageResult(qo.getCurrentPage(),qo.getPageSize(), Collections.emptyList(),rows);
        }
        qo.verifyCurrentPage(rows);
        List<Department> list = departmentMapper.queryByQueryObject(qo);
        PageResult pageResult = new PageResult(qo.getCurrentPage(), qo.getPageSize(), list, rows);
        return pageResult;
    }
}
