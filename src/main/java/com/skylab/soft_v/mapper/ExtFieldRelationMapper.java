package com.skylab.soft_v.mapper;

import com.skylab.soft_v.entity.ExtFieldRelation;

import java.util.List;

/**
 * 扩展字段关联表(ExtFieldRelation)表数据库访问层
 *
 * @author xw
 * @since 2020-08-12 18:07:47
 */
public interface ExtFieldRelationMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExtFieldRelation queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ExtFieldRelation> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param extFieldRelation 实例对象
     * @return 对象列表
     */
    List<ExtFieldRelation> queryByExample(ExtFieldRelation extFieldRelation);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<ExtFieldRelation> queryList();

    /**
     * 新增数据
     *
     * @param extFieldRelation 实例对象
     * @return 影响行数
     */
    int insert(ExtFieldRelation extFieldRelation);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param extFieldRelation 实例对象
     * @return 影响行数
     */
    int insertSelective(ExtFieldRelation extFieldRelation);

    /**
     * 修改数据
     *
     * @param extFieldRelation 实例对象
     * @return 影响行数
     */
    int update(ExtFieldRelation extFieldRelation);

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

    List<ExtFieldRelation> queryValidFieldsByCategory(int category);
}