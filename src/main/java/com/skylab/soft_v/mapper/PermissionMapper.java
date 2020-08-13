package com.skylab.soft_v.mapper;

import com.skylab.soft_v.entity.Permission;

import java.util.List;
import java.util.Set;

/**
 * 权限表(Permission)表数据库访问层
 *
 * @author xw
 * @since 2020-08-11 15:35:52
 */
public interface PermissionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Permission> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param permission 实例对象
     * @return 对象列表
     */
    List<Permission> queryByExample(Permission permission);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Permission> queryList();

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int insert(Permission permission);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int insertSelective(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int update(Permission permission);

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
     * 通过角色Id获取权限列表
     *
     * @param id 角色Id
     * @return 权限列表
     */
    Set<Permission> queryByRoleId(Integer id);
}