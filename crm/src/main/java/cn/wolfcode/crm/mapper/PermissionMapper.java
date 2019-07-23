package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;
import java.util.Set;

//权限数据的操作:
public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    List<Permission> selectAll();

    List<Permission> selectForList(QueryObject qo);

    Integer selectForCount(QueryObject qo);

    /**
     * 查询所有的权限表达式
     * @return
     */
    List<String> selectAllExpressions();

    Set<String> selectExpressionByEmpId(Long empId);

}