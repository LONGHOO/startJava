package cn.wolfcode.p2p.base.service.impl;

import cn.wolfcode.p2p.base.domain.SystemDictionaryItem;
import cn.wolfcode.p2p.base.mapper.SystemDictionaryItemMapper;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.query.SystemDictionaryQueryObject;
import cn.wolfcode.p2p.base.service.ISystemDictionaryItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-06-12 13:43
 * @Descrption
 **/
@Service
public class SystemDictionayItemServiceImpl implements ISystemDictionaryItemService{

    @Autowired
    private SystemDictionaryItemMapper dictionaryItemMapper;

    @Override
    public void saveOrUpdate(SystemDictionaryItem dictionaryItem) {
        if(dictionaryItem.getId() != null){
            dictionaryItemMapper.updateByPrimaryKey(dictionaryItem);
        }else{
            dictionaryItemMapper.insert(dictionaryItem);
        }
    }

    @Override
    public List<SystemDictionaryItem> selectAll() {
        return dictionaryItemMapper.selectAll();
    }

    @Override
    public PageInfo<SystemDictionaryItem> query(SystemDictionaryQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(dictionaryItemMapper.query(qo));
    }

    @Override
    public void deleteById(Long id) {
        dictionaryItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByParentId(Long parentId) {
        dictionaryItemMapper.deleteByParentId(parentId);
    }

    @Override
    public List<SystemDictionaryItem> getBySn(String educationBackground) {
        return dictionaryItemMapper.getBySn(educationBackground);
    }
}
