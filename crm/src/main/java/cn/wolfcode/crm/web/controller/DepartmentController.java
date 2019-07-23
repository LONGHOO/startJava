package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDepartmentService;
import cn.wolfcode.crm.util.ResultInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequiresPermissions(value = {"部门列表","department:list"},logical = Logical.OR)
    @RequestMapping("/list")
    public String list(@ModelAttribute QueryObject qo, Model model) {
        model.addAttribute("pageInfo", departmentService.query(qo));
        return "department/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdate(Department entity) {
        try{
            departmentService.saveOrUpdate(entity);
            return ResultInfo.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo delete(Long id) {
        try{
            departmentService.delete(id);
            return ResultInfo.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }
}
