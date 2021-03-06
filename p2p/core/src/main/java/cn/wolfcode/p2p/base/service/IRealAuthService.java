package cn.wolfcode.p2p.base.service;

import cn.wolfcode.p2p.base.domain.RealAuth;
import cn.wolfcode.p2p.base.query.RealAuthQueryObject;
import com.github.pagehelper.PageInfo;

/**
 * @Author: 十一
 * @Date: 2019-06-12 13:43
 * @Descrption
 **/
public interface IRealAuthService {

    void saveOrUpdate(RealAuth realAuth);

    RealAuth get(Long id);

    RealAuth getCurrent();

    PageInfo<RealAuth> query(RealAuthQueryObject qo);

    void audit(RealAuth realAuth);
}
