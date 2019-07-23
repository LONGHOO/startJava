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
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo")QueryObject qo, Model model){
        model.addAttribute("result",permissionService.query(qo));
        return "/permission/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONResult delete(Long id){
        JSONResult result = new JSONResult();
        try {
            permissionService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除失败了.");
        }
        return result;
    }

    /*@RequestMapping("/reload")
    public String reload(){
        permissionService.reload();
        return "redirect:/permission/list.do";
    }*/
    @RequestMapping("/reload")
    @ResponseBody
    public JSONResult reload(){
        JSONResult result = new JSONResult();
        try {
            permissionService.reload();
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("加载失败了.");
        }
        return result;
    }
}
