package com.skylab.soft_v.mapper;

import com.skylab.soft_v.entity.Category;

import java.util.List;

/**
 * 种类表（蓝牙、GNSS...）(Category)表数据库访问层
 *
 * @author xw
 * @since 2020-08-11 15:34:52
 */
public interface CategoryMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Category queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Category> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param category 实例对象
     * @return 对象列表
     */
    List<Category> queryByExample(Category category);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Category> queryList();

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 影响行数
     */
    int insert(Category category);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param category 实例对象
     * @return 影响行数
     */
    int insertSelective(Category category);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 影响行数
     */
    int update(Category category);

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