package cn.wolfcode.crud.mapper;

import cn.wolfcode.crud.domain.Permission;
import cn.wolfcode.crud.query.QueryObject;

import java.util.List;
import java.util.Set;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    List<Permission> selectAll();

    int selectForCount(QueryObject qo);

    List<Permission> selectForList(QueryObject qo);

    List<String> selectExpressions();

    Set<String> selectExpressionByEmpId(Long empId);

    //shiro授权查询权限
    List<String> getPermissionsByEmpId(Long id);
}