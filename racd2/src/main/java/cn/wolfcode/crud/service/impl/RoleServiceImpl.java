package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.mapper.RoleMapper;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        roleMapper.deleteByPrimaryKey(id);
        return  roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    public List<Role> selectAll() {
        List<Role> roles = roleMapper.selectAll();


        return roles;
    }

    @Override
    public PageResult query(QueryObject qo) {
        int rows = roleMapper.queryForCount(qo);
        if(rows==0){
            return new PageResult(qo.getCurrentPage(),qo.getPageSize(),0, Collections.EMPTY_LIST);
        }
        List<Department> list = roleMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),rows,list);
    }

    @Override
    public void saveOrUpdate(Role role) {
        if(role.getId()==null){
            roleMapper.insert(role);
        }else{
            roleMapper.updateByPrimaryKey(role);
        }
    }

    @Override
    public void updateRolePermission(Long id, String[] ids) {
        //先将当前角色的权限全部删除
        roleMapper.deletePermissionByRoleId(id);
        //添加权限
        roleMapper.insertPermissionWithRoleId(id,ids);
    }
}
