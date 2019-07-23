package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDepartmentService;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.service.IRoleService;
import cn.wolfcode.crm.util.ResultInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

    @RequiresPermissions(value = {"员工列表", "employee:list"},logical = Logical.OR)
    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) {
        model.addAttribute("pageInfo", employeeService.query(qo));
        model.addAttribute("depts", departmentService.list());
        return "employee/list";
    }

    @RequiresPermissions(value = {"员工编辑", "employee:input"},logical = Logical.OR)
    @RequestMapping("/input")
    public String input(Long id, Model model) {
        if (id != null) {
            model.addAttribute("employee", employeeService.get(id));
        }
        //查询所有部门
        model.addAttribute("depts", departmentService.list());
        //查询所有角色
        model.addAttribute("roles", roleService.list());
        return "employee/input";
    }

    @RequiresPermissions(value = {"员工保存或更新", "employee:saveOrUpdate"},logical = Logical.OR)
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdate(Employee entity, Long[] ids) {
        try {
            employeeService.saveOrUpdate(entity, ids);
            return ResultInfo.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }

    @RequiresPermissions(value = {"员工删除", "employee:delete"},logical = Logical.OR)
    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo delete(Long id) {
        try {
            employeeService.delete(id);
            return ResultInfo.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/batchDelete")
    public ResultInfo batchDelete(Long[] ids) {
        try {
            employeeService.batchDelete(ids);
            return ResultInfo.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public ResultInfo checkName(String name) {
        Integer result = employeeService.checkName(name);
        if(result > 0){
            return ResultInfo.fail("用户名已存在");
        }
        return ResultInfo.success();
    }

    @RequestMapping("/exportFile")
    public void exportTemplate(HttpServletResponse response) throws IOException {
        HSSFWorkbook wb = employeeService.exportFile();
        response.setHeader("Content-Disposition","attachment;filename=employee.xls");
        wb.write(response.getOutputStream());
    }


    @RequestMapping("/importFile")
    public String importFile(MultipartFile file){
        employeeService.importFile(file);
        return "redirect:/employee/list.do";
    }

}
