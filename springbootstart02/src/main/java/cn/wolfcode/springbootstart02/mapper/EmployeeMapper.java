package cn.wolfcode.springbootstart02.mapper;

import cn.wolfcode.springbootstart02.domain.Employee;
import cn.wolfcode.springbootstart02.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Employee record);

    void insertRelation(@Param("employeeId") Long employeeId,
                        @Param("roleId") Long roleId);

    void deleteRelation(Long employeeId);

    Employee selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Employee queryEmpByUserName(String username);

    void batchDelete(@Param("ids") Long[] ids);

    Integer chenckName(String username);

    List<String> querySnByPrimaryKey(Long id);

    List<Employee> selectAll();

    List<Employee> queryEmpByRoles(@Param("market_manager") String market_manager, @Param("market") String market);

    List<Employee> query(QueryObject qo);
}