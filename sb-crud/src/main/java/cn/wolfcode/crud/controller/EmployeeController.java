package cn.wolfcode.crud.controller;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IDepartmentService;
import cn.wolfcode.crud.service.IEmployeeService;
import cn.wolfcode.crud.service.IRoleService;
import cn.wolfcode.crud.util.JSONResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;

    //@RequiredPermission({"员工列表","employee:list"})
    //添加shiro注解
    //@RequiresPermissions(value={"员工列表","employee:list"},logical = Logical.OR)
    @RequestMapping("/list")
    public String list(@ModelAttribute("qo")QueryObject qo, Model model){
        model.addAttribute("result",employeeService.query(qo));
        model.addAttribute("depts",departmentService.list());
        //int i = 1/0;
        return "/employee/list";
    }

    //@RequiredPermission({"员工删除","employee:delete"})
    //@RequiresPermissions(value={"员工删除","employee:delete"},logical = Logical.OR)
    @RequestMapping("/delete")
    @ResponseBody
    public JSONResult delete(Long id){
        JSONResult result = new JSONResult();
        try {
            employeeService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除失败了.");
        }
        return result;
    }

    //@RequiredPermission({"员工编辑","employee:input"})
    //@RequiresPermissions(value={"员工编辑","employee:input"},logical = Logical.OR)
    @RequestMapping("/input")
    public String input(Long id,Model model){
        if (id != null){
            model.addAttribute("entity",employeeService.get(id));
        }
        //查询出所有部门,在员工编辑处回显
        model.addAttribute("depts",departmentService.list());
        model.addAttribute("roles",roleService.list());
        return "/employee/input";
    }

    //@RequiredPermission({"员工保存和更新","employee:saveOrUpdate"})
    //@RequiresPermissions(value={"员工保存和更新","employee:saveOrUpdate"},logical = Logical.OR)
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JSONResult saveOrUpdate(Employee entity,Long[] ids){
        JSONResult result = new JSONResult();
        try {
            employeeService.saveOrUpdate(entity,ids);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了.");
        }
        return result;
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public JSONResult batchDelete(Long[] ids){
        JSONResult result = new JSONResult();
        try {
            employeeService.batchDelete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("批量删除操作失败了.");
        }
        return result;
    }

    //员工信息导出
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        //创建工作薄之后并在service中往里面写数据
        HSSFWorkbook workbook = employeeService.exportExcel();
        //设置下载文件的名称
        response.setHeader("Content-Disposition","attachment;filename=employee.xls");
        workbook.write(response.getOutputStream());
    }

    //员工文件信息导入
    @RequestMapping("/importExcel")
    public String importExcel(MultipartFile file) throws IOException {
        //文件输入流创建工作薄,把文件里面的数据转换成一些对象
        HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
        //然后从工作薄里面获取,再保存到数据库中
        employeeService.importExcel(workbook);
        return "redirect:/employee/list.do";
    }
}














