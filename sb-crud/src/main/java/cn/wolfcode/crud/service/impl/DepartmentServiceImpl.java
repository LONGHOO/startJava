package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.mapper.DepartmentMapper;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void saveOrUpdate(Department entity) {
        if (entity.getId() == null){
            departmentMapper.insert(entity);
        }else{
            departmentMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void delete(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Department get(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> list() {
        return departmentMapper.selectAll();
    }

    @Override
    public PageInfo<Department> query(QueryObject qo) {
        //需要分页的sql方法前执行这代码
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Department> pageList = departmentMapper.selectForList(qo);
        return new PageInfo<>(pageList);










        /*int rows = departmentMapper.selectForCount(qo);
        if(rows==0){
            return new PageResult(qo.getPageSize());
        }
        List<Department> list = departmentMapper.selectForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),rows,list);*/
    }
}
