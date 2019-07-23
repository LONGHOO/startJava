package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.mapper.RoleMapper;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void saveOrUpdate(Role entity, Long[] ids) {
        if (entity.getId() == null){
            roleMapper.insert(entity);
        }else{
            //删除旧的关系
            roleMapper.deleteByRolePermission(entity.getId());
            roleMapper.updateByPrimaryKey(entity);
        }
        //新增维护关系
        if (ids != null){
            for (Long permissionId : ids) {
                roleMapper.insertByRolePermission(entity.getId(),permissionId);
            }
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null){
            roleMapper.deleteByRolePermission(id);
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

    @Override
    public PageInfo<Role> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Role> pageList = roleMapper.selectForList(qo);
        return new PageInfo<>(pageList);
    }
}
