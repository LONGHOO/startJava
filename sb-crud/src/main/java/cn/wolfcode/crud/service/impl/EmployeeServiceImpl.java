package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.mapper.EmployeeMapper;
import cn.wolfcode.crud.mapper.PermissionMapper;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IEmployeeService;
import cn.wolfcode.crud.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void saveOrUpdate(Employee entity, Long[] ids) {
        if (entity.getId() == null){
            //密码加密,使用用户名作为加密的"盐
            //entity.setPassword(new Md5Hash(entity.getPassword(),entity.getName()).toString());
            employeeMapper.insert(entity);
        }else{
            //删除关系
            employeeMapper.deleteRelation(entity.getId());
            employeeMapper.updateByPrimaryKey(entity);
        }

        //维护关系员工和角色的关系
        if(ids!=null){
            for (Long roleId : ids) {
                employeeMapper.insertRelation(entity.getId(),roleId);
            }
        }
    }

    @Override
    public void delete(Long id) {
        if (id!=null){
            //删除员工的时候要先删除关系
            employeeMapper.deleteRelation(id);
            employeeMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Employee> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Employee> pageList = employeeMapper.selectForList(qo);
        return new PageInfo<>(pageList);
    }

    @Override
    public void login(String username, String password) {
        //selectByUsernameAndPassword方法查询用户信息(账户和密码)
        Employee currentEmp = employeeMapper.selectByUsernameAndPassword(username,password);
        if(currentEmp == null){//信息不存在登录失败
            //登录失败
            throw new RuntimeException("账号密码不匹配");
        }
        //共享用户信息到session中,表现层获取session对象
        /*RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) attributes;
        requestAttributes
                .getRequest()
                .getSession()
                .setAttribute("EMPLOYEE_IN_SESSION",currentEmp);*/
        //代码抽取到工具类,注意信息是共享进域中,注意代码提示点到是获取的方法,应是设置进
        UserContext.setCurrentUser(currentEmp);//把获取到的账户信息,共享到session域中

        //共享当前用户拥有的权限信息,根据id查所有权限表达式
        Set<String> expressions = permissionMapper.selectExpressionByEmpId(currentEmp.getId());
        /*requestAttributes
                .getRequest()
                .getSession()
                .setAttribute("EXPRESSIONS_IN_SESSION",expressions);*/
        //代码抽取到工具类,注意信息是共享进域中,注意代码提示点到是获取的方法,应是设置进
        UserContext.setExpressions(expressions);//获取当前用户需要拥有的权限表达式共享到session域中
    }

    @Override
    public void batchDelete(Long[] ids) {
        employeeMapper.batchDelete(ids);
    }

    @Override
    public HSSFWorkbook exportExcel() {
        //创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //工作薄有了,然后添加纸张
        HSSFSheet sheet = workbook.createSheet("员工信息");
        //纸张里面第一行往往是数据信息的表头,创建出来(三个单元格),填内容
        HSSFRow hander = sheet.createRow(0);

        HSSFCell name = hander.createCell(0);//第一个单元格
        name.setCellValue("姓名");

        HSSFCell email = hander.createCell(1);//第二个单元格
        email.setCellValue("邮箱");

        HSSFCell age = hander.createCell(2);//第三个单元格
        age.setCellValue("年龄");

        //然后查询所有员工数据
        List<Employee> employees = employeeMapper.selectAll();
        //然后写在创建的纸张上
        for (int i = 0; i < employees.size(); i++){
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(employees.get(i).getName());
            row.createCell(1).setCellValue(employees.get(i).getEmail());
            row.createCell(2).setCellValue(employees.get(i).getAge());
        }
        return workbook;
    }

    //文件数据导入
    @Override
    public void importExcel(HSSFWorkbook workbook) {
        //获取纸张
        HSSFSheet sheet = workbook.getSheet("员工信息");
        //获取纸张除去第一行表头的所有员工数据
        int rowNum = sheet.getLastRowNum();
        for (int i = 1; i <= rowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            //获取单元格的内容
            String name = row.getCell(0).getStringCellValue();
            String email = row.getCell(1).getStringCellValue();
            Double cellValue = row.getCell(2).getNumericCellValue();
            int age = cellValue.intValue();
            //获取内容后保存到数据库
            Employee employee = new Employee();
            employee.setName(name);
            employee.setEmail(email);
            employee.setAge(age);
            //设置默认密码
            // Md5Hash md5Hash = new Md5Hash("1", name);
            //employee.setPassword(md5Hash.toString());
            employeeMapper.insert(employee);
        }
    }

    @Override
    public List<Employee> listEmployeeByRoleSns(String... sns) {
        return employeeMapper.listEmployeeByRoleSns(sns);
    }
}
