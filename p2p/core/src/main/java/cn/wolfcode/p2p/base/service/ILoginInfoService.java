package cn.wolfcode.p2p.base.service;

import cn.wolfcode.p2p.base.domain.LoginInfo;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-06-12 13:43
 * @Descrption
 **/
public interface ILoginInfoService {

    void saveOrUpdate(LoginInfo logininfo);

    int countByUsername(String username);

    void register(String username, String verifyCode, String password, String comfirmPwd);

    LoginInfo login(String username, String password, int userType);

    LoginInfo existAdmin(String defaultAdminAccount, String defaultAdminPassword, int usertypeManager);

    List<LoginInfo> selectByAuditor();
}
