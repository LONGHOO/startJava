package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.service.IRoleService;
import cn.wolfcode.crm.util.ResultInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    public String list(@ModelAttribute QueryObject qo, Model model) {
        model.addAttribute("pageInfo", roleService.query(qo));
        return "role/list";
    }

    @RequestMapping("/input")
    public String input(Long id, Model model) {
        if (id != null) {
            model.addAttribute("entity", roleService.get(id));
        }
        //查询所有的权限信息
        model.addAttribute("permissions",permissionService.list());
        return "role/input";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdate(Role entity,Long[] ids) {
        try{
            roleService.saveOrUpdate(entity,ids);
            return ResultInfo.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions(value ={"角色删除","role:delete"} ,logical = Logical.OR)
    public ResultInfo delete(Long id) {
        try{
            roleService.delete(id);
            return ResultInfo.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }
}
