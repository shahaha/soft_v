package com.skylab.soft_v.service;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.ByteSize;

import java.util.List;

/**
 * 字节大小(ByteSize)表服务接口
 *
 * @author xw
 * @since 2020-08-11 15:34:48
 */
public interface ByteSizeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ByteSize queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<ByteSize> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<ByteSize> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param byteSize 实例对象
     * @return 对象列表
     */
    List<ByteSize> queryByExample(ByteSize byteSize);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param byteSize 实例对象
     * @param page     查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    Pager<ByteSize> queryByExampleAndPage(ByteSize byteSize, int page, int limit);

    /**
     * 新增数据
     *
     * @param byteSize 实例对象
     * @return 实例对象
     */
    ByteSize insert(ByteSize byteSize);

    /**
     * 新增数据 可以有空字段
     *
     * @param byteSize 实例对象
     * @return 实例对象
     */
    ByteSize insertSelective(ByteSize byteSize);

    /**
     * 修改数据
     *
     * @param byteSize 实例对象
     * @return 实例对象
     */
    ByteSize update(ByteSize byteSize);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}