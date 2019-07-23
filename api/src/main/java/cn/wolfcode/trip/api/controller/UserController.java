package cn.wolfcode.trip.api.controller;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 十一
 * @Date: 2019-05-17 11:56
 * @Descrption 用户注册
 **/
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITravelService travelService;

    @PostMapping
    public Result regeister(User user){
        try{
            userService.regeister(user);
        }catch(Exception e){
            e.printStackTrace();
            return Result.getResult("当前邮箱已被注册！",null);
        }
        return Result.getResult(null,null);
    }

    @PutMapping("{id}")
    public Result updateUserInfo(User user){
        userService.updateUserInfo(user);
        return Result.getResult(null,user);
    }

    @GetMapping("{id}")
    public Result getUserInfo(@PathVariable("id") Long id){
        if(null != id){
            User user = userService.getUserById(id);
            return Result.getResult(null,user);
        }
        return Result.getResult("用户id为null",null);
    }

    @RequestMapping("{authorId}/travel")
    public PageInfo travelList(TravelQueryObject qo){
        qo.setOrderBy("t.lastUpdateTime desc");
        return travelService.query(qo);
    }

}
