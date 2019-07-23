package cn.wolfcode.p2p.base.mapper;

import cn.wolfcode.p2p.base.domain.LoginInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LoginInfo record);

    LoginInfo selectByPrimaryKey(Long id);

    List<LoginInfo> selectAll();

    int updateByPrimaryKey(LoginInfo record);

    int countByUsername(String username);

    LoginInfo checkUser(@Param("username") String username, @Param("password") String password, @Param("userType") int userType);

    LoginInfo existAdmin(@Param("defaultAdminAccount") String defaultAdminAccount, @Param("defaultAdminPassword") String defaultAdminPassword, @Param("usertypeManager") int usertypeManager);

   List<LoginInfo> selectByAuditor();
}