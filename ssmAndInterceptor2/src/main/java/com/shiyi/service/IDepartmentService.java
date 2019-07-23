package com.shiyi.service;

import com.shiyi.domain.Department;
import com.shiyi.query.QueryObject;

import javax.management.Query;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-20 17:36
 * @Descrption
 **/
public interface IDepartmentService {

    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    PageResult getPageResultByQueryObject(QueryObject queryObject);
}
