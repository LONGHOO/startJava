package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User checkEmailAndPassword(@Param("email") String email, @Param("password") String password);

    List<User> query(QueryObject qo);

    void updateUserInfo(User user);

    User getUserById(Long id);
}