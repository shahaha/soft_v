package com.skylab.soft_v.mapper;

import com.skylab.soft_v.entity.Log;

import java.util.List;

/**
 * 日志表(Log)表数据库访问层
 *
 * @author xw
 * @since 2020-08-11 15:35:52
 */
public interface LogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Log queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Log> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param conditions 条件
     * @return 对象列表
     */
    List<Log> queryByExample(String conditions);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Log> queryList();

    /**
     * 新增数据
     *
     * @param log 实例对象
     * @return 影响行数
     */
    int insert(Log log);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param log 实例对象
     * @return 影响行数
     */
    int insertSelective(Log log);

    /**
     * 修改数据
     *
     * @param log 实例对象
     * @return 影响行数
     */
    int update(Log log);

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