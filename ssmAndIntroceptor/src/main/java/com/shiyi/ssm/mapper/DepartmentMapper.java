package com.shiyi.ssm.mapper;

import com.shiyi.ssm.domain.Department;
import com.shiyi.ssm.query.QueryObject;

import java.util.List;


/**
 * @author 十一
 * department接口
 */
public interface DepartmentMapper {


    /**
     * 用来完成
     * @param id
     * @return 受影响的行数
     */
    int deleteByPrimaryKey(Long id);


    /**
     * 插入记录
     * @param record
     * @return 受影响的行数
     *
     */
    int insert(Department record);


    /**
     * 查询出满足条件的数据
     * @param id
     * @return 查询出得Department对象
     */
    Department selectByPrimaryKey(Long id);

    /**
     * 查询所有
     * @return list
     */
    List<Department> selectAll();

    /**
     * 更新记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(Department record);

    /**
     * 返回列表
     * @param qo
     * @return
     */
    List<Department> queryByQueryObject(QueryObject qo);

    /**
     * 返回数量
     * @param qo
     * @return
     */
    Integer queryRowsByQueryObejct(QueryObject qo);

    Department queryByMultiParams(Long id,String name,String sn);
}