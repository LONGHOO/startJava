package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmployeeService {
    void saveOrUpdate(Employee entity, Long[] ids);

    void delete(Long id);

    Employee get(Long id);

    PageInfo<Employee> query(QueryObject qo);

    Employee queryEmpByUserName(String username);

    void batchDelete(Long[] ids);

    Integer checkName(String username);

    List<String> querySnByPrimaryKey(Long id);

    HSSFWorkbook exportFile();

    void importFile(MultipartFile file);

    List<Employee> queryEmpByRoles(String market_manager, String market);
}
