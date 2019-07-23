package cn.wolfcode.p2p.bussniss;

import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.bussniss.domain.SystemAccount;
import cn.wolfcode.p2p.bussniss.service.ISystemAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: 十一
 * @Date: 2019-06-22 18:39
 * @Descrption
 **/
@Component
public class listenner implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ISystemAccountService systemAccountService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //如果不存在系统账号
        System.out.println("检测是否存在系统账号");
        if(!systemAccountService.existAccount()){
            SystemAccount systemAccount = new SystemAccount();
            systemAccount.setVersion(0);
            systemAccount.setFreezedAmount(Constants.ZERO);
            systemAccount.setUsableAmount(Constants.ZERO);
            systemAccountService.saveOrUpdate(systemAccount);
        }
    }
}
