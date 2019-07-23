package cn.wolfcode.springbootstart02.controller;

import cn.wolfcode.springbootstart02.domain.Employee;
import cn.wolfcode.springbootstart02.query.QueryObject;
import cn.wolfcode.springbootstart02.service.IEmployeeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private ApplicationArguments args;

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model){
        System.out.println(System.getProperties());
        System.out.println(args.getNonOptionArgs().toString());
        PageInfo<Employee> pageInfo = employeeService.query(qo);
        model.addAttribute("pageInfo", pageInfo);
        return "employee/list";
    }
}
