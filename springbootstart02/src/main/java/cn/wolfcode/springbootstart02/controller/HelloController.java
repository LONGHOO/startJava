package cn.wolfcode.springbootstart02.controller;

import cn.wolfcode.springbootstart02.domain.Employee;
import cn.wolfcode.springbootstart02.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;


/**
 * @Author: 十一
 * @Date: 2019-06-10 12:59
 * @Descrption
 **/
@Controller
public class HelloController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IEmployeeService service;
    @RequestMapping("hello")
    public String hello(Model model){
        model.addAttribute("hello", "hello world");
        return "index";
    }

    @RequestMapping("employee")
    @ResponseBody
    public Object employee(Long id){
        return service.get(id);
    }

    @RequestMapping("save")
    public String save(){
        Employee employee = new Employee();
        employee.setAdmin(true);
        employee.setAge(13);
        employee.setEmail("1231@qq.com");
        employee.setName("name");
        employee.setPassword("helseor");
        service.saveOrUpdate(employee);
        return "success";
    }
}
