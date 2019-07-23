package cn.wolfcode.crud.service;

import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IRoleService {

    void saveOrUpdate(Role entity, Long[] ids);

    void delete(Long id);

    Role get(Long id);

    List<Role> list();

    PageInfo<Role> query(QueryObject qo);

}
