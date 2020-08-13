package com.skylab.soft_v.mapper;

import com.skylab.soft_v.entity.SoftTool;

import java.util.List;

/**
 * 软件工具表（测试、烧录工具）(SoftTool)表数据库访问层
 *
 * @author xw
 * @since 2020-08-12 18:07:51
 */
public interface SoftToolMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SoftTool queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SoftTool> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param softTool 实例对象
     * @return 对象列表
     */
    List<SoftTool> queryByExample(SoftTool softTool);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<SoftTool> queryList();

    /**
     * 新增数据
     *
     * @param softTool 实例对象
     * @return 影响行数
     */
    int insert(SoftTool softTool);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param softTool 实例对象
     * @return 影响行数
     */
    int insertSelective(SoftTool softTool);

    /**
     * 修改数据
     *
     * @param softTool 实例对象
     * @return 影响行数
     */
    int update(SoftTool softTool);

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