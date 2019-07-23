package cn.wolfcode.crud.controller;

import cn.wolfcode.crud.domain.Systemdictionaryitem;
import cn.wolfcode.crud.query.SystemDictionaryQueryObject;
import cn.wolfcode.crud.service.ISystemdictionaryService;
import cn.wolfcode.crud.service.ISystemdictionaryitemService;
import cn.wolfcode.crud.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemdictionaryItem")
public class SystemdictionaryitemController {

    @Autowired
    private ISystemdictionaryitemService systemdictionaryitemService;
    @Autowired
    private ISystemdictionaryService systemdictionaryService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo")SystemDictionaryQueryObject qo, Model model){
        if (qo.getParentId() != null) {
            //查询目录标题对象,进行封装
            model.addAttribute("parent",systemdictionaryService.get(qo.getParentId()));
            model.addAttribute("result", systemdictionaryitemService.query(qo));
        }
        //查询出所有字典目录
        model.addAttribute("dics",systemdictionaryService.list());
        return "/systemdictionaryItem/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONResult delete(Long id){
        JSONResult result = new JSONResult();
        try {
            systemdictionaryitemService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除失败了.");
        }
        return result;
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JSONResult saveOrUpdate(Systemdictionaryitem entity){
        JSONResult result = new JSONResult();
        try {
            systemdictionaryitemService.saveOrUpdate(entity);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了.");
        }
        return result;
    }
}
