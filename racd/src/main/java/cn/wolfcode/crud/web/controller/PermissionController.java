package cn.wolfcode.crud.web.controller;

import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IPermissionService;
import cn.wolfcode.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService service;

    @RequestMapping("/list")
    @RequiredPermission({"表达式列表", "permission:list"})
    public String list(Model model, QueryObject qo) {
        PageResult pageResult = service.query(qo);
        model.addAttribute("menu","permission");
        model.addAttribute("pageResult",pageResult);
        return "permission/list";
    }

    @RequiredPermission({"删除表达式", "permission:delete"})
    @RequestMapping("/delete")
    public String delete(Model model, Long id) {
        service.deleteByPrimaryKey(id);
        return "redirect:/permission/list.do";
    }

    @RequestMapping("/reload")
    public String reloadHandle() {
        return "";
    }
}
