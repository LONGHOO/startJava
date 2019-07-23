package cn.wolfcode.crud.service;

import cn.wolfcode.crud.domain.Permission;
import cn.wolfcode.crud.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IPermissionService {

    void delete(Long id);

    List<Permission> list();

    PageInfo<Permission> query(QueryObject qo);

    void reload();
}
