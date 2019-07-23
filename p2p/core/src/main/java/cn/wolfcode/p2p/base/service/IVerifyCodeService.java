package cn.wolfcode.p2p.base.service;

import javax.servlet.http.HttpSession;

/**
 * @Author: 十一
 * @Date: 2019-06-12 20:09
 * @Descrption
 **/
public interface IVerifyCodeService {

    void sendVerifyCode(String username);

    boolean checkVerifyCode(String username,String verifyCode);
}
