package cn.wolfcode.trip.api.controller;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.service.IStrategyCommentService;
import cn.wolfcode.trip.base.util.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-05-22 19:21
 * @Descrption
 **/
@RestController
@RequestMapping("strategyComments")
public class StrategyCommentController {

    @Autowired
    private IStrategyCommentService strategyCommentService;

    @GetMapping("hotStrategyComments")
    public PageInfo<StrategyComment> getHotStrategyComments(StrategyQueryObject qo){
        qo.setOrderBy("s.createTime desc");
        List<StrategyComment> list = strategyCommentService.queryList(qo);
        return new PageInfo<>(list);
    }

    @PostMapping("{strategy.id}")
    public Result saveComment(StrategyComment comment,String[] tags){
        strategyCommentService.saveOrUpdate(comment,tags);
        return Result.getResult(null, null);
    }
}
