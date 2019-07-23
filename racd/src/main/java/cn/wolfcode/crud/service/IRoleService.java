package cn.wolfcode.crud.service;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;

import java.util.List;

public interface IRoleService {
    int deleteByPrimaryKey(Long id);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    PageResult query(QueryObject qo);

    void  saveOrUpdate(Role role);

    void updateRolePermission(Long id, String[] ids);
}
