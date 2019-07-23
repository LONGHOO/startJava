package com.shiyi.ssm.service;

import com.shiyi.ssm.domain.Department;
import com.shiyi.ssm.query.QueryObject;
import com.shiyi.ssm.utils.PageResult;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-19 19:47
 * @Descrption
 **/
public interface IDepartmentService {

    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    PageResult getPageResult(QueryObject qo);
}
