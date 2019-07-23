package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IRoleService {
    void saveOrUpdate(Role entity,Long[] ids);
    void delete(Long id);

    Role get(Long id);
    List<Role> list();

    PageInfo<Role> query(QueryObject qo);
}
