package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.UserMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-05-17 19:00
 * @Descrption
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkEmail(String email) {
        User user = userMapper.checkEmailAndPassword(email,null);
        if(user != null){
            throw new RuntimeException("该邮箱已被注册！");
        }
        return user;
    }

    @Override
    public User checkEmailAndPassword(String email, String password) {
        User user = userMapper.checkEmailAndPassword(email,password);
        if(user == null){
            throw new RuntimeException("用户名或密码错误");
        }
        return user;
    }

    @Override
    public void regeister(User user) {
        User temp = checkEmail(user.getEmail());
        if(temp != null){
            throw new RuntimeException("用户名或密码错误");
        }
        //设置用户默认的头像
        user.setHeadImgUrl("/img/user/head.jpg");
        //设置用户默认的背景
        user.setCoverImgUrl("/img/user/bg.jpeg");
        userMapper.insert(user);
    }

    @Override
    public PageInfo<User> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<User> list = userMapper.query(qo);
        return new PageInfo<>(list);
    }

    @Override
    public void updateUserInfo(User user) {
        userMapper.updateUserInfo(user);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

}
