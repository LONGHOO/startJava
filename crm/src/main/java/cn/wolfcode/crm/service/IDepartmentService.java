package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {
    void saveOrUpdate(Department entity);
    void delete(Long id);

    Department get(Long id);
    List<Department> list();

    PageInfo<Department> query(QueryObject qo);
}
