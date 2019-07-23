package com.shiyi.web.controller;

import com.shiyi.domain.Department;
import com.shiyi.query.QueryObject;
import com.shiyi.service.IDepartmentService;
import com.shiyi.service.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: 十一
 * @Date: 2019-04-20 17:27
 * @Descrption
 **/
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/list")
    public String listHandle(Model model, QueryObject queryObject){
        PageResult pageResult = departmentService.getPageResultByQueryObject(queryObject);
        model.addAttribute("page",pageResult);
        model.addAttribute("qo",queryObject);
        return "list";
    }

    @RequestMapping("/input")
    public String inputHandler(Model model,Long id){
        if(id != null){
            Department department = departmentService.selectByPrimaryKey(id);
            model.addAttribute("department", department);
        }
        return "input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdateHandler(Department department){
        if(department.getId() != null){
            departmentService.updateByPrimaryKey(department);
        }else{
            departmentService.insert(department);
        }
        return "redirect:/department/list.do";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String deleteHandler(Long id){
        if(id != null){
            departmentService.deleteByPrimaryKey(id);
        }
        return "redirect:/department/list.do";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public String deleteHandlerByRestful(Long id, Model mv){
        if(id != null){
           // departmentService.deleteByPrimaryKey(id);
        }
        mv.addAttribute("msg","delete method");
        return "list";
    }

}
