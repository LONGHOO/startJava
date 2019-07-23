package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.mapper.TagMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.ITagService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

@Service

public class TagServiceImpl implements ITagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public void saveOrUpdate(Tag tag) {
        if(null == tag.getId()){
            tagMapper.insert(tag);
        }else{
            tagMapper.updateByPrimaryKey(tag);
        }
    }

    @Override
    public List<Tag> listAll() {
        return tagMapper.selectAll();
    }

    @Override
    public List<Tag> getHotTag(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        return tagMapper.getHotTag();
    }

    @Override
    public Tag getById(Long id) {
        return null;
    }


}
