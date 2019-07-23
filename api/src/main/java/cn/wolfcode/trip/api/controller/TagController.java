package cn.wolfcode.trip.api.controller;

import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.ITagService;
import cn.wolfcode.trip.base.util.Result;
import cn.wolfcode.trip.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-05-22 19:21
 * @Descrption
 **/
@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private ITagService tagService;

    @GetMapping("hotTags")
    public Result getHotTags(QueryObject qo){
        User user = UserContext.getUserInSession();
        System.out.println(user.getId());
        qo.setCurrentPage(0);
        qo.setPageSize(6);
        List<Tag> list = tagService.getHotTag(qo);
        return Result.getResult(null, list);
    }
}
