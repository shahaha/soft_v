package com.skylab.soft_v.service;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Category;

import java.util.List;

/**
 * 种类表（蓝牙、GNSS...）(Category)表服务接口
 *
 * @author xw
 * @since 2020-08-11 15:34:52
 */
public interface CategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Category queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<Category> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Category> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param category 实例对象
     * @return 对象列表
     */
    List<Category> queryByExample(Category category);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param category 实例对象
     * @param page     查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    Pager<Category> queryByExampleAndPage(Category category, int page, int limit);

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category insert(Category category);

    /**
     * 新增数据 可以有空字段
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category insertSelective(Category category);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category update(Category category);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}