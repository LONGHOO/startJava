package cn.wolfcode.p2p.base.mgrsite.listener;

import cn.wolfcode.p2p.base.domain.LoginInfo;
import cn.wolfcode.p2p.base.service.ILoginInfoService;
import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.base.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: 十一
 * @Date: 2019-06-15 21:04
 * @Descrption
 **/
@Component
public class ContextListerner implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ILoginInfoService loginInfoService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LoginInfo loginInfo = loginInfoService.existAdmin(Constants.DEFAULT_ADMIN_ACCOUNT,
                MD5.encode(Constants.DEFAULT_ADMIN_PASSWORD), LoginInfo.USERTYPE_MANAGER);
        if(loginInfo == null){
            LoginInfo info = new LoginInfo();
            info.setUserType(LoginInfo.USERTYPE_MANAGER);
            info.setUsername(Constants.DEFAULT_ADMIN_ACCOUNT);
            info.setPassword(MD5.encode(Constants.DEFAULT_ADMIN_PASSWORD));
            info.setState(LoginInfo.STATE_NORMAL);
            loginInfoService.saveOrUpdate(info);
        }
    }
}
