package com.skylab.soft_v.mapper;

import com.skylab.soft_v.entity.Chip;

import java.util.List;

/**
 * 芯片表(Chip)表数据库访问层
 *
 * @author xw
 * @since 2020-08-11 15:35:53
 */
public interface ChipMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Chip queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Chip> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param chip 实例对象
     * @return 对象列表
     */
    List<Chip> queryByExample(Chip chip);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Chip> queryList();

    /**
     * 新增数据
     *
     * @param chip 实例对象
     * @return 影响行数
     */
    int insert(Chip chip);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param chip 实例对象
     * @return 影响行数
     */
    int insertSelective(Chip chip);

    /**
     * 修改数据
     *
     * @param chip 实例对象
     * @return 影响行数
     */
    int update(Chip chip);

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