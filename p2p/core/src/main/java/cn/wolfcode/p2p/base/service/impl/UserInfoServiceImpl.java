package cn.wolfcode.p2p.base.service.impl;

import cn.wolfcode.p2p.base.domain.LoginInfo;
import cn.wolfcode.p2p.base.domain.UserInfo;
import cn.wolfcode.p2p.base.mapper.UserInfoMapper;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 十一
 * @Date: 2019-06-12 13:43
 * @Descrption
 **/
@Service
@Transactional
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     *功能描述 保存或更新用户基本信息
     * @author 十一
     * @param userInfo
     * @return void
     * @date 2019-06-16 20:51
     */
    @Override
    public void saveOrUpdate(UserInfo userInfo) {

        //从数据库中查询当前的用户信息，版本号等
        UserInfo info = this.get(userInfo.getId());
        //初始化Userinfo
        if(info == null){
            userInfoMapper.insert(userInfo);
        }else{
            userInfo.setVersion(info.getVersion());
            userInfoMapper.updateByPrimaryKey(userInfo);
        }
    }

    @Override
    public UserInfo getCurrent() {
        LoginInfo loginInfo = UserContext.getCurrentUser();
        return this.get(loginInfo.getId());
    }

    @Override
    public UserInfo get(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateRealAuthState(Long id, Long realAuthId) {
        userInfoMapper.updateRealAuthState(id,realAuthId);
    }
}
