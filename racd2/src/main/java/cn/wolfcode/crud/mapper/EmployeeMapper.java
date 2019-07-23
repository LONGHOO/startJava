package cn.wolfcode.crud.mapper;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    int queryForCount(QueryObject qo);
    List<Employee> queryForList(QueryObject qo );

    void  insertRelationship(@Param("employeeId") Long employeeId, @Param("roleId") Long roleId);
    void  deleteRelationship(Long employeeId);

    Employee getEmpByUserNameAndPassword(@Param("username") String username, @Param("password") String password);

    Set<String> queryPermissionByEmpId(Long id);
}