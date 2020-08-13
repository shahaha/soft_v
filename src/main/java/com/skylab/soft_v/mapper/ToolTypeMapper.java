package com.skylab.soft_v.mapper;

import com.skylab.soft_v.entity.ToolType;

import java.util.List;

/**
 * 软件工具类型表(ToolType)表数据库访问层
 *
 * @author xw
 * @since 2020-08-12 18:07:51
 */
public interface ToolTypeMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ToolType queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ToolType> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param toolType 实例对象
     * @return 对象列表
     */
    List<ToolType> queryByExample(ToolType toolType);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<ToolType> queryList();

    /**
     * 新增数据
     *
     * @param toolType 实例对象
     * @return 影响行数
     */
    int insert(ToolType toolType);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param toolType 实例对象
     * @return 影响行数
     */
    int insertSelective(ToolType toolType);

    /**
     * 修改数据
     *
     * @param toolType 实例对象
     * @return 影响行数
     */
    int update(ToolType toolType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询数据总行数
     *
     * @return 总行数
     */
    int count();
}