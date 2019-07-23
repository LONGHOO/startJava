package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author 十一
**/
public interface RegionMapper {

    /**
     * 根据id删除
     * @param id 需要删除的id
     * @return 受影响的条数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增的数据
     * @param record 新增的region数据
     * @return 受影响的条数
     */
    int insert(Region record);

    /**
     *功能描述：根据父节点id查询地区信息
     * @param parentId 父节点id，顶级状态的父节点为null
     * @return java.util.List<cn.wolfcode.trip.base.domain.Region>
     * @date 2019-05-26 11:25
     */
    List<Region> selectByParentId(@Param("parentId") Long parentId);

    /**
     *功能描述：根据状态查询所有的数据，state可以为null
     * @author 十一
     * @param state 状态
     * @return java.util.List<cn.wolfcode.trip.base.domain.Region>
     * @date 2019-05-26 11:26
     */
    List<Region> selectAll(@Param("state") Integer state);


    /**
     * 根据id更新
     * @param record 地区信息
     * @return 受影响的行数
     */
    int updateByPrimaryKey(Region record);


    /**
     * 根据id改变state状态
     * @param region 地区信息
     */
    void changeState(Region region);
    
}