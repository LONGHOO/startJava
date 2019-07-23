package cn.wolfcode.crud.service;

import cn.wolfcode.crud.domain.Permission;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-05-03 13:37
 * @Descrption
 **/
public interface IPermissionService {

    int deleteByPrimaryKey(Long id);

    List<Permission> selectAll();

    PageResult query(QueryObject qo);

    Integer reloadPermissions();

    List<Permission> queryPermissionById(Long id);
}
