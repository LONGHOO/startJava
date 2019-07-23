package cn.wolfcode.crud.service;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.query.QueryObject;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface IEmployeeService {

    void saveOrUpdate(Employee entity, Long[] ids);

    void delete(Long id);

    Employee get(Long id);

    PageInfo<Employee> query(QueryObject qo);

    void login(String username, String password);

    void batchDelete(Long[] ids);

    //表格查询数据
    HSSFWorkbook exportExcel();


    void importExcel(HSSFWorkbook workbook);

    List<Employee> listEmployeeByRoleSns(String... sns);
}
