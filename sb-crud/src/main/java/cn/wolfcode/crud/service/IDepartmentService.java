package cn.wolfcode.crud.service;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {

    void saveOrUpdate(Department entity);

    void delete(Long id);

    Department get(Long id);

    List<Department> list();

    PageInfo<Department> query(QueryObject qo);

}
