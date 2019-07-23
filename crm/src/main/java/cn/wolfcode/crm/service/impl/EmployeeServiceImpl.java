package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PageInfo<Employee> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Employee> list = employeeMapper.selectForList(qo);
        return new PageInfo<>(list);
    }



    @Override
    public Employee queryEmpByUserName(String username) {
        return employeeMapper.queryEmpByUserName(username);
    }

    @Override
    public void batchDelete(Long[] ids) {
        employeeMapper.batchDelete(ids);
    }

    @Override
    public Integer checkName(String username) {
        return employeeMapper.chenckName(username);
    }

    @Override
    public List<String> querySnByPrimaryKey(Long id) {
        return employeeMapper.querySnByPrimaryKey(id);
    }

    @Override
    public HSSFWorkbook exportFile() {
        //创建工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建工作表
        HSSFSheet sheet = wb.createSheet("员工信息表");
        //创建标题行
        HSSFRow headRow = sheet.createRow(0);
        //分别创建，姓名，邮箱，年龄
        headRow.createCell(0).setCellValue("姓名");
        headRow.createCell(1).setCellValue("邮箱");
        headRow.createCell(2).setCellValue("年龄");
        //查询Employee列表
        List<Employee> employees = employeeMapper.selectAll();
        for (int i = 0; i < employees.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            Employee emp = employees.get(i);
            row.createCell(0).setCellValue(emp.getName());
            row.createCell(1).setCellValue(emp.getEmail());
            row.createCell(2).setCellValue(emp.getAge());
        }
        return wb;
    }

    @Override
    public void importFile(MultipartFile file) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheet = wb.getSheetAt(0);
            int rownum = sheet.getLastRowNum();
            Employee emp = new Employee();
            for (int i = 1; i <= rownum; i++) {
                HSSFRow row = sheet.getRow(i);
                emp.setName(row.getCell(0).getStringCellValue());
                emp.setEmail(row.getCell(1).getStringCellValue());
                emp.setAge(new BigDecimal(row.getCell(2).getNumericCellValue()).intValue());
                emp.setPassword(new Md5Hash("1",emp.getName(),10).toString());
                employeeMapper.insert(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> queryEmpByRoles(String market_manager, String market) {
        return employeeMapper.queryEmpByRoles(market_manager,market);
    }

    @Override
    public void saveOrUpdate(Employee entity, Long[] ids) {
        if (entity.getId() == null) {
            SimpleHash newPwd = new SimpleHash("MD5", entity.getPassword(), entity.getName(), 10);
            entity.setPassword(newPwd.toString());
            employeeMapper.insert(entity);
        } else {
            //删除旧的关系
            employeeMapper.deleteRelation(entity.getId());
            employeeMapper.updateByPrimaryKey(entity);
        }
        //同步保存关系
        if (ids != null) {
            for (Long roleId : ids) {
                employeeMapper.insertRelation(entity.getId(), roleId);
            }
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            //先删除关系,再删除员工
            employeeMapper.deleteRelation(id);
            employeeMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }
}
