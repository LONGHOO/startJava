package cn.wolfcode.p2p.base.service.impl;

import cn.wolfcode.p2p.base.domain.Account;
import cn.wolfcode.p2p.base.domain.IpLog;
import cn.wolfcode.p2p.base.domain.LoginInfo;
import cn.wolfcode.p2p.base.domain.UserInfo;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.mapper.IpLogMapper;
import cn.wolfcode.p2p.base.mapper.LoginInfoMapper;
import cn.wolfcode.p2p.base.service.IAccountService;
import cn.wolfcode.p2p.base.service.ILoginInfoService;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.service.IVerifyCodeService;
import cn.wolfcode.p2p.base.util.AssertUtil;
import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.base.util.MD5;
import cn.wolfcode.p2p.base.util.UserContext;
import java.util.List;

import cn.wolfcode.p2p.bussniss.domain.ExpAcount;
import cn.wolfcode.p2p.bussniss.service.IExpAccountFlowService;
import cn.wolfcode.p2p.bussniss.service.IExpAccountService;
import com.github.pagehelper.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-06-12 13:43
 * @Descrption
 **/
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class LoginInfoServiceImpl implements ILoginInfoService {

    @Autowired
    private IExpAccountService expAccountService;

    @Autowired
    private IExpAccountFlowService expAccountFlowService;

    @Autowired
    private LoginInfoMapper mapper;

    @Autowired
    private IpLogMapper ipLogMapper;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IVerifyCodeService verifyCodeService;

    @Autowired
    private IAccountService accountService;

    @Override
    public void saveOrUpdate(LoginInfo logininfo) {
        mapper.insert(logininfo);
    }

    @Override
    public int countByUsername(String username) {
        return mapper.countByUsername(username);
    }

    /**
     *功能描述 注册用户
     * @author 十一
     * @param username, verifyCode, password, comfirmPwd
     * @return void
     * @date 2019-06-12 19:57
     */
    @Override
    public void register(String username, String verifyCode, String password, String comfirmPwd) {
        //判断不能为null
        AssertUtil.hasLength(username,"用户名不能为空");
        AssertUtil.hasLength(verifyCode,"验证码不能为空");
        AssertUtil.hasLength(password,"密码不能为空");
        AssertUtil.hasLength(comfirmPwd,"确认密码不能为空");
        //是否是正确的手机号码格式
        AssertUtil.isPhoneNumber(username);
        boolean result = verifyCodeService.checkVerifyCode(username, verifyCode);
        if(!result){
            throw new DisplayException("验证码不正确");
        }
        //判断密码
        AssertUtil.isEquals(password,comfirmPwd,"两次密码不一致");
        //判断用户名是否已经存在
        int count = mapper.countByUsername(username);
        if(count > 0){
            throw new DisplayException("当前手机号已被注册");
        }
        //注册
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername(username);
        loginInfo.setPassword(MD5.encode(password));
        loginInfo.setState(LoginInfo.STATE_NORMAL);
        loginInfo.setUserType(LoginInfo.USERTYPE_USER);
        mapper.insert(loginInfo);
        //初始化用户的信息
        UserInfo userInfo = new UserInfo();
        userInfo.setId(loginInfo.getId());
        userInfo.setPhoneNumber(loginInfo.getUsername());
        userInfo.setVersion(0);
        userInfoService.saveOrUpdate(userInfo);
        //初始化用户账户
        Account account = new Account();
        account.setId(loginInfo.getId());
        account.setVersion(0);
        accountService.insert(account);
        //新增体验金
        ExpAcount exp = new ExpAcount();
        exp.setFreezedAmount(Constants.ZERO);
        exp.setId(loginInfo.getId());
        exp.setUnReturnExpAmount(Constants.ZERO);
        //注册送体验金
        exp.setUsableAmount(Constants.EXP_ACCOUNT_REGIESTER);
        exp.setVersion(0);
        //保存注册用户送的体验金
        expAccountService.addExpAccount(exp);
        //保存赠送体验金流水
        expAccountFlowService.addExpAccountFlow(Constants.EXP_ACCOUNT_REGIESTER,Constants.EXP_STATE_NORMAL
            ,"注册赠送体验金"+Constants.EXP_ACCOUNT_REGIESTER+"元",exp);
    }

    @Override
    public LoginInfo login(String username, String password, int userType) {
        LoginInfo loginInfo = mapper.checkUser(username, password,userType);
        IpLog ipLog = new IpLog();
        if(null == loginInfo){
           ipLog.setState(IpLog.STATE_FAILED);
        }else{
            ipLog.setState(IpLog.STATE_SUCCESS);
        }
        UserContext.setLoginInfoInSession(loginInfo);
        //保存用户登陆日志
        ipLog.setIp(UserContext.getRequest().getRemoteHost());
        ipLog.setUsername(username);
        ipLog.setLogintime(new Date());
        //普通用户
        ipLog.setUsertype(userType);
        ipLogMapper.insert(ipLog);
        return loginInfo;
    }

    @Override
    public LoginInfo existAdmin(String defaultAdminAccount, String defaultAdminPassword, int usertypeManager) {
        LoginInfo loginInfo = mapper.existAdmin(defaultAdminAccount, defaultAdminPassword, usertypeManager);
        return loginInfo;
    }

    @Override
    public List<LoginInfo> selectByAuditor() {
        return mapper.selectByAuditor();
    }


}
