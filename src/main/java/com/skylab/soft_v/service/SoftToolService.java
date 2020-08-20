package com.skylab.soft_v.service;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.SoftTool;

import java.util.List;

/**
 * 软件工具表（测试、烧录工具）(SoftTool)表服务接口
 *
 * @author xw
 * @since 2020-08-12 18:07:50
 */
public interface SoftToolService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SoftTool queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<SoftTool> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<SoftTool> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param softTool 实例对象
     * @return 对象列表
     */
    List<SoftTool> queryByExample(SoftTool softTool);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param toolName 实例对象
     * @param page     查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    Pager<SoftTool> queryByNameAndPage(String toolName, int page, int limit);

    /**
     * 新增数据
     *
     * @param softTool 实例对象
     * @return 实例对象
     */
    SoftTool insert(SoftTool softTool);

    /**
     * 新增数据 可以有空字段
     *
     * @param softTool 实例对象
     * @return 实例对象
     */
    SoftTool insertSelective(SoftTool softTool);

    /**
     * 修改数据
     *
     * @param softTool 实例对象
     * @return 实例对象
     */
    SoftTool update(SoftTool softTool);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 根据类型查工具
     * @param i 类型id
     * @return 工具列表
     */
    List<SoftTool> queryByType(int i);

    /**
     * 判断是否在使用
     * @param id id
     * @return 是否使用
     */
    boolean inUser(Integer id);
}