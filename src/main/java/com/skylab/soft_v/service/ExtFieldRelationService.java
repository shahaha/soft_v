package com.skylab.soft_v.service;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.ExtFieldRelation;

import java.util.List;

/**
 * 扩展字段关联表(ExtFieldRelation)表服务接口
 *
 * @author xw
 * @since 2020-08-12 18:07:46
 */
public interface ExtFieldRelationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExtFieldRelation queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<ExtFieldRelation> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<ExtFieldRelation> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param extFieldRelation 实例对象
     * @return 对象列表
     */
    List<ExtFieldRelation> queryByExample(ExtFieldRelation extFieldRelation);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param extFieldRelation 实例对象
     * @param page             查询起始位置
     * @param limit            查询条数
     * @return 对象列表
     */
    Pager<ExtFieldRelation> queryByExampleAndPage(ExtFieldRelation extFieldRelation, int page, int limit);

    /**
     * 新增数据
     *
     * @param extFieldRelation 实例对象
     * @return 实例对象
     */
    ExtFieldRelation insert(ExtFieldRelation extFieldRelation);

    /**
     * 新增数据 可以有空字段
     *
     * @param extFieldRelation 实例对象
     * @return 实例对象
     */
    ExtFieldRelation insertSelective(ExtFieldRelation extFieldRelation);

    /**
     * 修改数据
     *
     * @param extFieldRelation 实例对象
     * @return 实例对象
     */
    ExtFieldRelation update(ExtFieldRelation extFieldRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}