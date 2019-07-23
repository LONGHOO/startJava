package cn.wolfcode.crud.web.controller;


import cn.wolfcode.crud.domain.Permission;
import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;

import cn.wolfcode.crud.service.IPermissionService;
import cn.wolfcode.crud.service.IRoleService;
import cn.wolfcode.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    @RequiredPermission({"角色列表", "role:list"})
    public String list(Model model, QueryObject qo) {
        PageResult pageResult = roleService.query(qo);
        model.addAttribute("pageResult", pageResult);
        return "role/list";
    }

    @RequestMapping("/delete")
    @RequiredPermission({"角色删除", "role:delete"})
    public String delete(Model model, Long id) {
        roleService.deleteByPrimaryKey(id);
        return "redirect:/role/list.do";
    }

    @RequestMapping("/input")
    @RequiredPermission({"角色录入", "role:input"})
    public String input(Model model, Long id) {
        if (id != null) {
            Role role = roleService.selectByPrimaryKey(id);
            model.addAttribute("entity", role);
//            //查询所有的权限
        }
        List<Permission> allPermissions = permissionService.selectAll();
        model.addAttribute("allPermissions", allPermissions);
        return "role/input";
    }

    @RequiredPermission({"角色新增", "role:saveOrUpdate"})
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(@RequestParam(value="ids") String[] ids,Role role) {
        roleService.saveOrUpdate(role);
        roleService.updateRolePermission(role.getId(),ids);
        return "redirect:/role/list.do";
    }
}
