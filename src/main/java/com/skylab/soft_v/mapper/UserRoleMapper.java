package com.skylab.soft_v.mapper;

import com.skylab.soft_v.entity.UserRole;

import java.util.List;

/**
 * 用户_角色表(UserRole)表数据库访问层
 *
 * @author xw
 * @since 2020-08-11 15:35:54
 */
public interface UserRoleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserRole queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserRole> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userRole 实例对象
     * @return 对象列表
     */
    List<UserRole> queryByExample(UserRole userRole);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<UserRole> queryList();

    /**
     * 新增数据
     *
     * @param userRole 实例对象
     * @return 影响行数
     */
    int insert(UserRole userRole);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param userRole 实例对象
     * @return 影响行数
     */
    int insertSelective(UserRole userRole);

    /**
     * 修改数据
     *
     * @param userRole 实例对象
     * @return 影响行数
     */
    int update(UserRole userRole);

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

    /**
     * 通过用户Id删除
     * @param id 用户Id
     */
    void deleteByUserId(Integer id);
}