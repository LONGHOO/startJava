package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

/**
 * @Author: 十一
 * @Date: 2019-05-17 18:59
 * @Descrption
 **/
public interface IUserService {

    User checkEmail(String email);

    User checkEmailAndPassword(String email,String password);

    void regeister(User user);

    PageInfo<User> query(QueryObject qo);

    void updateUserInfo(User user);

    User getUserById(Long id);
}
