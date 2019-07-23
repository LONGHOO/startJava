package cn.wolfcode.crud.controller;

import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IPermissionService;
import cn.wolfcode.crud.service.IRoleService;
import cn.wolfcode.crud.util.JSONResult;
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
    public String list(@ModelAttribute("qo")QueryObject qo, Model model){
        model.addAttribute("result",roleService.query(qo));
        return "/role/list";
    }
    @RequestMapping("/delete")
    @ResponseBody
    public JSONResult delete(Long id){
        JSONResult result = new JSONResult();
        try {
            roleService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除失败了.");
        }
        return result;
    }
    @RequestMapping("/input")
    public String input(Long id,Model model){
        if (id != null){
            model.addAttribute("entity",roleService.get(id));
        }
        //查询所有权限信息
        model.addAttribute("permission",permissionService.list());
        return "/role/input";
    }
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JSONResult saveOrUpdate(Role entity,Long[] ids){
        JSONResult result = new JSONResult();
        try {
            roleService.saveOrUpdate(entity,ids);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了.");
        }
        return result;
    }
}
