package cn.wolfcode.crud.web.controller;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IDepartmentService;
import cn.wolfcode.crud.service.IEmployeeService;
import cn.wolfcode.crud.service.IRoleService;
import cn.wolfcode.util.RequiredPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/list")
    @RequiredPermission({"员工列表","employee:list"})
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        PageResult pageResult = employeeService.query(qo);
        model.addAttribute("pageResult", pageResult);
        List<Department> depts = departmentService.selectAll();
        model.addAttribute("depts", depts);
        return "employee/list";
    }

    @RequestMapping("/delete")
    @RequiredPermission({"员工删除","employee:delete"})
    public String delete(Model model, Long id) {
        employeeService.deleteByPrimaryKey(id);
        return "redirect:/employee/list.do";
    }

    @RequestMapping("/input")
    @RequiredPermission({"员工删除","employee:delete"})
    public String input(Model model, Long id) {
        if (id != null) {

            Employee employee = employeeService.selectByPrimaryKey(id);
            model.addAttribute("entity", employee);
        }
        List<Department> departments = departmentService.selectAll();
        model.addAttribute("depts", departments);
        List<Role> roles = roleService.selectAll();
        model.addAttribute("roles", roles);
        return "employee/input";
    }

    @RequestMapping("/saveOrUpdate")
    @RequiredPermission({"员工新增","employee:saveOrUpdate"})
    public String saveOrUpdate(Model model, Employee employee, Long[] ids) {

        employeeService.saveOrUpdate(employee, ids);

        return "redirect:/employee/list.do";
    }
}
