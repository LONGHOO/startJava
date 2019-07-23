package cn.wolfcode.trip.api.controller;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.Result;
import cn.wolfcode.trip.base.util.UserContext;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 十一
 * @Date: 2019-05-17 19:15
 * @Descrption
 **/
@RestController
@RequestMapping("sessions")
public class SessionController {

    @Autowired
    private IUserService userService;

    /**
     *
     * 功能描述: 用户登陆
     * @param:  用户信息
     * @return: 登陆状态/错误信息/用户信息
     * @auther: 十一
     */
    @PostMapping
    public Result loginIn(User user){
        if(!(StringUtils.hasLength(user.getEmail()) && StringUtils.hasLength(user.getPassword()))){
            return Result.getResult("用户名或密码为空", null);
        }
        try{
            user = userService.checkEmailAndPassword(user.getEmail(), user.getPassword());
        }catch(Exception e){
            e.printStackTrace();
            return Result.getResult("用户名或密码不正确",null);
        }
        //保存用户信息
        UserContext.setUserInSession(user);
        return Result.getResult(null,user);
    }

    /**
     *
     * 功能描述: 用户注销
     * @param:  null
     * @return: 操作状态
     * @auther: 十一
     */
    @DeleteMapping
    public Result logout(){
        UserContext.getSession().invalidate();
        return Result.getResult(null, null);
    }
}
