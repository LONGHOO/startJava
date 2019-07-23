package cn.wolfcode.p2p.base.service.impl;

import cn.wolfcode.p2p.base.domain.SystemDictionary;
import cn.wolfcode.p2p.base.mapper.SystemDictionaryMapper;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.service.ISystemDictionaryItemService;
import cn.wolfcode.p2p.base.service.ISystemDictionaryService;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 十一
 * @Date: 2019-06-12 13:43
 * @Descrption
 **/
@Service
public class SystemDictionayServiceImpl implements ISystemDictionaryService {

    @Autowired
    private SystemDictionaryMapper dictionaryMapper;

    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;

    @Override
    public void saveOrUpdate(SystemDictionary dictionary) {
        if(dictionary.getId() == null){
            dictionaryMapper.insert(dictionary);
        }else{
            dictionaryMapper.updateByPrimaryKey(dictionary);
        }
    }

    @Override
    public List<SystemDictionary> selectAll() {
        java.util.List<SystemDictionary> list = dictionaryMapper.selectAll();
        return list;
    }

    @Override
    public PageInfo<SystemDictionary> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        return new PageInfo<>(dictionaryMapper.query(qo));
    }

    @Override
    public void deleteById(Long id) {
        //删除所有的字典详情
        systemDictionaryItemService.deleteByParentId(id);
        //删除字典
        dictionaryMapper.deleteByPrimaryKey(id);
    }
}
