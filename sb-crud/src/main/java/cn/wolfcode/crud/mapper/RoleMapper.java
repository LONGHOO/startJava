package cn.wolfcode.crud.mapper;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    int selectForCount(QueryObject qo);

    List<Role> selectForList(QueryObject qo);

    void insertByRolePermission(@Param("roleId") Long roleId,
                                @Param("permissionId") Long permissionId);

    void deleteByRolePermission(Long roleId);

    //shiro授权查角色
    List<String> getRolesByEmpId(Long id);
}