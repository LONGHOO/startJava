package cn.wolfcode.p2p.base.mapper;

import cn.wolfcode.p2p.base.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);

    void updateRealAuthState(@Param("id") Long id, @Param("realAuthId") Long realAuthId);
}