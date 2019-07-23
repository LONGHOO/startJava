package cn.wolfcode.crud.mapper;

import cn.wolfcode.crud.domain.Department;
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

    int queryForCount(QueryObject qo);
    List<Department> queryForList(QueryObject qo );

    void deletePermissionByRoleId(Long id);

    void insertPermissionWithRoleId(@Param("id") Long id, @Param("ids") String[] ids);
}