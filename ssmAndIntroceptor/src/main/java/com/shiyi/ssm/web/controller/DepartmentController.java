package com.shiyi.ssm.web.controller;

import com.shiyi.ssm.domain.Department;
import com.shiyi.ssm.query.QueryObject;
import com.shiyi.ssm.service.IDepartmentService;
import com.shiyi.ssm.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-19 18:57
 * @Descrption
 **/
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/list")
    public String departmentList(Model model, QueryObject qo){
        PageResult pageResult = departmentService.getPageResult(qo);
        model.addAttribute("pageResult", pageResult);
        model.addAttribute("qo",qo);
        return "list";
    }

    @RequestMapping("/input")
    public String departmentInput(Model model,Long id){
        if(id != null){
            model.addAttribute("entity",departmentService.selectByPrimaryKey(id));
        }
        return "input";
    }

    @RequestMapping("/saveOrUpdate")
    public String departmentSaveOrUpdate(Model model,Department department){
        if(department.getId() != null){
            departmentService.updateByPrimaryKey(department);
        }else{
            departmentService.insert(department);
        }
        return "redirect:/department/list.do";
    }

    @RequestMapping("/delete")
    public String departmentDelete(Long id){
        if(id != null){
            departmentService.deleteByPrimaryKey(id);
        }
        return "redirect:/department/list.do";
    }
}
