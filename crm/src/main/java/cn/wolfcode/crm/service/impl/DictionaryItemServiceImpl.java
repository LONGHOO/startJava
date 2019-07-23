package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.mapper.SystemDictionaryItemMapper;
import cn.wolfcode.crm.query.DictionaryItemQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDictionaryItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-05-10 19:30
 * @Descrption
 **/
@Service
public class DictionaryItemServiceImpl implements IDictionaryItemService {

    @Autowired
    private SystemDictionaryItemMapper mapper;


    @Override
    public void saveOrUpdate(SystemDictionaryItem entity) {
        if (entity.getId() != null) {
            mapper.updateByPrimaryKey(entity);
        } else {
            mapper.insert(entity);
        }
    }

    @Override
    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SystemDictionaryItem> queryItemBySn(String sn) {
        return mapper.queryItemBySn(sn);
    }

    @Override
    public SystemDictionaryItem get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemDictionaryItem> list() {
        return mapper.selectAll();
    }

    @Override
    public PageInfo<SystemDictionaryItem> query(DictionaryItemQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(),"sequence desc");
        List<SystemDictionaryItem> items = mapper.selectByParentId(qo);
        return new PageInfo<>(items);
    }
}
