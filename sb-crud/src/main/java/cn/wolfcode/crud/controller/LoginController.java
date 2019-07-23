package cn.wolfcode.crud.controller;

import cn.wolfcode.crud.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        try {
            employeeService.login(username,password);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errorMsg",e.getMessage());
            return "forward:/login.jsp";
        }
        return "redirect:/department/list.do";
    }
}
