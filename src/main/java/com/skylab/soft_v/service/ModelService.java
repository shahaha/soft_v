package com.skylab.soft_v.service;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Model;

import java.util.List;

/**
 * 型号表(Model)表服务接口
 *
 * @author xw
 * @since 2020-08-11 15:35:50
 */
public interface ModelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Model queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<Model> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Model> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param model 实例对象
     * @return 对象列表
     */
    List<Model> queryByExample(Model model);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param model 实例对象
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<Model> queryByExampleAndPage(Model model, int page, int limit);

    /**
     * 新增数据
     *
     * @param model 实例对象
     * @return 实例对象
     */
    Model insert(Model model);

    /**
     * 新增数据 可以有空字段
     *
     * @param model 实例对象
     * @return 实例对象
     */
    Model insertSelective(Model model);

    /**
     * 修改数据
     *
     * @param model 实例对象
     * @return 实例对象
     */
    Model update(Model model);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}