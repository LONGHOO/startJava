package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    Integer selectForCount(QueryObject qo);

    List<Role> selectForList(QueryObject qo);

    void insertRolePermissionRelation(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    void deleteRolePermissionRelation(Long roleId);
}