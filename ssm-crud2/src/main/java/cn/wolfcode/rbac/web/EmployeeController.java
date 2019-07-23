package cn.wolfcode.rbac.web;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.qo.PageResult;
import cn.wolfcode.rbac.qo.QueryObject;
import cn.wolfcode.rbac.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 十一
 * @Date: 2019-04-30 11:25
 * @Descrption
 **/
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/list")
    public String employeeList(QueryObject queryObject, Model model){
        PageResult<Employee> pageResult = employeeService.queryOfPageResult(queryObject);
        model.addAttribute("page",pageResult);
        model.addAttribute("qo",queryObject);
        return "/employee/list";
    }


    @RequestMapping("/delete")
    public String delete(Long id) {
        employeeService.delete(id);
        return "redirect:/employee/list.do";
    }
    @RequestMapping("/input")
    public String input(Long id,Model model) {
        if (id != null) {
            model.addAttribute("entity",employeeService.get(id));
        }
        return "/employee/input";
    }


    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Employee employee) {
        employeeService.saveOrUpdate(employee);

        return "redirect:/employee/list.do";
    }
}
