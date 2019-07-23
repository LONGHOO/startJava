package cn.wolfcode.crud.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author: 十一
 * @Date: 2019-05-03 19:27
 * @Descrption
 **/
@Controller
public class LogOutController {

    @RequestMapping("/logout")
    public String logoutHandle(HttpSession session){
        session.invalidate();
        return "redirect:/login.jsp";
    }
}
