package com.skylab.soft_v.service;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Chip;

import java.util.List;

/**
 * 芯片表(Chip)表服务接口
 *
 * @author xw
 * @since 2020-08-11 15:35:53
 */
public interface ChipService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Chip queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<Chip> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Chip> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param chip 实例对象
     * @return 对象列表
     */
    List<Chip> queryByExample(Chip chip);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param chip  实例对象
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<Chip> queryByExampleAndPage(Chip chip, int page, int limit);

    /**
     * 新增数据
     *
     * @param chip 实例对象
     * @return 实例对象
     */
    Chip insert(Chip chip);

    /**
     * 新增数据 可以有空字段
     *
     * @param chip 实例对象
     * @return 实例对象
     */
    Chip insertSelective(Chip chip);

    /**
     * 修改数据
     *
     * @param chip 实例对象
     * @return 实例对象
     */
    Chip update(Chip chip);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}