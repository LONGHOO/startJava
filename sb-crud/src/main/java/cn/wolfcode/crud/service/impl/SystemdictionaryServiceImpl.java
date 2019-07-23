package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Systemdictionary;
import cn.wolfcode.crud.mapper.SystemdictionaryMapper;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.ISystemdictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemdictionaryServiceImpl implements ISystemdictionaryService {

    @Autowired
    private SystemdictionaryMapper systemdictionaryMapper;

    @Override
    public void saveOrUpdate(Systemdictionary entity) {
        if (entity.getId() == null){
            systemdictionaryMapper.insert(entity);
        }else{
            systemdictionaryMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void delete(Long id) {
        systemdictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Systemdictionary get(Long id) {
        return systemdictionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Systemdictionary> list() {
        return systemdictionaryMapper.selectAll();
    }

    @Override
    public PageInfo<Systemdictionary> query(QueryObject qo) {
        //需要分页的sql方法前执行这代码
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Systemdictionary> pageList = systemdictionaryMapper.selectForList(qo);
        return new PageInfo<>(pageList);
    }
}
