package cn.wolfcode.p2p.base.service;

import cn.wolfcode.p2p.base.domain.UserInfo;

/**
 * @Author: 十一
 * @Date: 2019-06-12 13:43
 * @Descrption
 **/
public interface IUserInfoService {

    void saveOrUpdate(UserInfo userInfo);

    UserInfo getCurrent();

    UserInfo get(Long id);

    void updateRealAuthState(Long id, Long realAuthId);
}
