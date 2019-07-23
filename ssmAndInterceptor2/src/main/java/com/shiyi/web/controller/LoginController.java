package com.shiyi.web.controller;

import com.shiyi.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: 十一
 * @Date: 2019-04-21 22:46
 * @Descrption
 **/
@Controller
@SessionAttributes("user")
public class LoginController {

    @RequestMapping("/login")
    public String loginHandler(User user, HttpSession session){
        System.out.println(user);
        if(!user.getName().equals("zhangsan") && !user.getPassword().equals("zhang")){
            return "redirect:/login.jsp";
        }
        session.setAttribute("USER_IN_SESSION","user");
        return "redirect:/department/list.do";
    }

    @ModelAttribute
    public void modelAttribute(@RequestParam("name")String name, Map<String,Object> map){
        User user = new User();
        user.setName("zhangsan");
        user.setPassword("zhang");
        map.put("user",user);
    }
}
