package cn.wolfcode.crud.web.controller;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.service.IEmployeeService;
import cn.wolfcode.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 十一
 * @Date: 2019-05-03 19:01
 * @Descrption
 **/

@Controller
public class LoginController {

    @Autowired
    private IEmployeeService service;

    @RequestMapping("/login")
    public String loginHandle(String username, String password, Model model){
       try{
           service.checkUserByUsernameAndPassword(username, password);
       }catch(Exception e){
           e.printStackTrace();
           Result result = Result.getResult(e.getMessage(), null);
           model.addAttribute("result",result);
           return "forward:/login.jsp";
       }
       return "redirect:/department/list.do";
    }
}
