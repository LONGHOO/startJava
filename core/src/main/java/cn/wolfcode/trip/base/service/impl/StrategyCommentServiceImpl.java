package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.mapper.StrategyCommentMapper;
import cn.wolfcode.trip.base.mapper.TagMapper;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.service.IStrategyCommentService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyCommentServiceImpl implements IStrategyCommentService {

    @Autowired
    private StrategyCommentMapper strategyCommentMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public void saveOrUpdate(StrategyComment strategyComment,String[] tags) {
        if(null == strategyComment.getId()){
            strategyComment.setUser(UserContext.getUserInSession());
            strategyCommentMapper.insert(strategyComment);
            //保存标签
            for (String t : tags) {
                Tag tag = new Tag();
                tag.setName(t);
                tagMapper.insert(tag);
                //保存标签和comment的对应关系
                tagMapper.saveRelation(strategyComment.getId(),tag.getId());
            }
        }
    }

    @Override
    public List<StrategyContent> listAll() {
        return null;
    }

    @Override
    public StrategyComment getById(Long id) {
        return null;
    }

    @Override
    public List<StrategyComment> queryList(StrategyQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        return strategyCommentMapper.queryList(qo);
    }


}
