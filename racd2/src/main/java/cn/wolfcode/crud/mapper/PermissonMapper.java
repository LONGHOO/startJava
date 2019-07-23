package cn.wolfcode.crud.mapper;

import cn.wolfcode.crud.domain.Permission;
import cn.wolfcode.crud.query.QueryObject;

import java.util.List;

public interface PermissonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    List<Permission> selectAll();

    Integer queryForCount(QueryObject qo);

    List<Permission> queryForList(QueryObject qo);

    void deleteAll();

    List<String> getAllPermissionsOfPerssionName();

    List<Permission> queryPermissionById(Long id);

    List<Permission> queryAll();

}