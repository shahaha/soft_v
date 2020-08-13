package com.skylab.soft_v.mapper;

import com.skylab.soft_v.entity.Model;

import java.util.List;

/**
 * 型号表(Model)表数据库访问层
 *
 * @author xw
 * @since 2020-08-11 15:35:51
 */
public interface ModelMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Model queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Model> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param model 实例对象
     * @return 对象列表
     */
    List<Model> queryByExample(Model model);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Model> queryList();

    /**
     * 新增数据
     *
     * @param model 实例对象
     * @return 影响行数
     */
    int insert(Model model);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param model 实例对象
     * @return 影响行数
     */
    int insertSelective(Model model);

    /**
     * 修改数据
     *
     * @param model 实例对象
     * @return 影响行数
     */
    int update(Model model);

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