package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.mapper.RoleMapper;
import cn.wolfcode.crm.query.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Role> list = roleMapper.selectForList(qo);
        return new PageInfo<>(list);
    }

    @Override
    public void saveOrUpdate(Role entity,Long[] ids) {
        if (entity.getId() == null) {
            roleMapper.insert(entity);
        } else {
            roleMapper.updateByPrimaryKey(entity);
            //删除角色和权限的旧关系
            roleMapper.deleteRolePermissionRelation(entity.getId());
        }

        //保存角色和权限的关系
        if(ids != null) {
            for (Long id : ids) {
                roleMapper.insertRolePermissionRelation(entity.getId(),id);
            }
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            roleMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> list() {
        return roleMapper.selectAll();
    }
}
