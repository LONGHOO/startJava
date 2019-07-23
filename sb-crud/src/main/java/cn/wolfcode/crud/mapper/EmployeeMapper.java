package cn.wolfcode.crud.mapper;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    //List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    int selectForCount(QueryObject qo);

    List<Employee> selectForList(QueryObject qo);

    //添加角色,维护与员工关系的接口
    void insertRelation(@Param("employeeId")Long employeeId,
                        @Param("roleId")Long roleId);

    //删除员工用到的删除关系接口
    void deleteRelation(Long employeeId);

    Employee selectByUsernameAndPassword(@Param("username") String username,
                                         @Param("password") String password);

    void batchDelete(Long[] ids);

    Employee selectByUsername(String username);

    List<Employee> selectAll();

    List<Employee> listEmployeeByRoleSns(String[] sns);
}