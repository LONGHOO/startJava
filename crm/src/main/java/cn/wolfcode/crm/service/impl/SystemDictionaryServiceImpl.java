package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.SystemDictionary;
import cn.wolfcode.crm.mapper.SystemDictionaryMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryServiceImpl implements IDictionaryService {

    @Autowired
    private SystemDictionaryMapper SystemDictionaryMapper;

    @Override
    public void saveOrUpdate(SystemDictionary entity) {
        if(entity.getId()!=null){
            SystemDictionaryMapper.updateByPrimaryKey(entity);
        }else{
            SystemDictionaryMapper.insert(entity);
        }
    }

    @Override
    public void delete(Long id) {
        SystemDictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SystemDictionary get(Long id) {
        return SystemDictionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemDictionary> list() {
        return SystemDictionaryMapper.selectAll();
    }

    @Override
    public PageInfo<SystemDictionary> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<SystemDictionary> systemDictionaries = SystemDictionaryMapper.selectAll();
        return new PageInfo<>(systemDictionaries);
    }
}
