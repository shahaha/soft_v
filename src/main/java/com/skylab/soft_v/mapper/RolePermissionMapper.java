package com.skylab.soft_v.mapper;

import com.skylab.soft_v.entity.RolePermission;

import java.util.List;

/**
 * 角色_权限表(RolePermission)表数据库访问层
 *
 * @author xw
 * @since 2020-08-11 15:35:48
 */
public interface RolePermissionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RolePermission queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RolePermission> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param rolePermission 实例对象
     * @return 对象列表
     */
    List<RolePermission> queryByExample(RolePermission rolePermission);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<RolePermission> queryList();

    /**
     * 新增数据
     *
     * @param rolePermission 实例对象
     * @return 影响行数
     */
    int insert(RolePermission rolePermission);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param rolePermission 实例对象
     * @return 影响行数
     */
    int insertSelective(RolePermission rolePermission);

    /**
     * 修改数据
     *
     * @param rolePermission 实例对象
     * @return 影响行数
     */
    int update(RolePermission rolePermission);

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
     * 删除角色
     * @param id 角色id
     */
    int deleteByRoleId(Integer id);
}