package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Systemdictionaryitem;
import cn.wolfcode.crud.mapper.SystemdictionaryitemMapper;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.ISystemdictionaryitemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemdictionaryitemServiceImpl implements ISystemdictionaryitemService {

    @Autowired
    private SystemdictionaryitemMapper systemdictionaryitemMapper;

    @Override
    public void saveOrUpdate(Systemdictionaryitem entity) {
        if (entity.getId() == null){
            systemdictionaryitemMapper.insert(entity);
        }else{
            systemdictionaryitemMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void delete(Long id) {
        systemdictionaryitemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Systemdictionaryitem get(Long id) {
        return systemdictionaryitemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Systemdictionaryitem> list() {
        return systemdictionaryitemMapper.selectAll();
    }

    @Override
    public PageInfo<Systemdictionaryitem> query(QueryObject qo) {
        //需要分页的sql方法前执行这代码
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(),"sequence");
        List<Systemdictionaryitem> pageList = systemdictionaryitemMapper.selectForList(qo);
        return new PageInfo<>(pageList);
    }

    @Override
    public List<Systemdictionaryitem> listItemByDicSn(String dicSn) {
        return systemdictionaryitemMapper.selectItemByDicSn(dicSn);
    }
}
