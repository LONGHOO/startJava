package cn.wolfcode.crud.web.controller;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IDepartmentService;
import cn.wolfcode.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService service;

    @RequestMapping("/list")
    @RequiredPermission({"部门列表","department:list"})
    public String list(Model model, QueryObject qo) {
        PageResult query = service.query(qo);
        model.addAttribute("pageResult",query);
        model.addAttribute("queryObject",qo);
        model.addAttribute("menu", "department");
        return "department/list";
    }

    @RequiredPermission({"部门删除","department:delete"})
    @RequestMapping("/delete")
    public String delete(Long id) {
        service.deleteByPrimaryKey(id);
        return "redirect:/department/list.do";
    }

    @RequestMapping("/input")
    @RequiredPermission({"部门录入","department:input"})
    public String input(Model model, Long id) {
        Department department = service.selectByPrimaryKey(id);
        model.addAttribute("department",department);
        model.addAttribute("menu", "department");
        return "department/input";
    }

    @RequiredPermission({"部门修改","department:saveOrUpdate"})
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Model model, Department department) {
        service.saveOrUpdate(department);
        return "redirect:/department/list.do";
    }
}
