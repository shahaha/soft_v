package com.skylab.soft_v.service;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.ToolType;

import java.util.List;

/**
 * 软件工具类型表(ToolType)表服务接口
 *
 * @author xw
 * @since 2020-08-12 18:07:51
 */
public interface ToolTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ToolType queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<ToolType> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<ToolType> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param toolType 实例对象
     * @return 对象列表
     */
    List<ToolType> queryByExample(ToolType toolType);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param toolType 实例对象
     * @param page     查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    Pager<ToolType> queryByExampleAndPage(ToolType toolType, int page, int limit);

    /**
     * 新增数据
     *
     * @param toolType 实例对象
     * @return 实例对象
     */
    ToolType insert(ToolType toolType);

    /**
     * 新增数据 可以有空字段
     *
     * @param toolType 实例对象
     * @return 实例对象
     */
    ToolType insertSelective(ToolType toolType);

    /**
     * 修改数据
     *
     * @param toolType 实例对象
     * @return 实例对象
     */
    ToolType update(ToolType toolType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}