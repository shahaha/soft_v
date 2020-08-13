package com.skylab.soft_v.service;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.RolePermission;

import java.util.List;

/**
 * 角色_权限表(RolePermission)表服务接口
 *
 * @author xw
 * @since 2020-08-11 15:35:47
 */
public interface RolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RolePermission queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<RolePermission> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<RolePermission> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param rolePermission 实例对象
     * @return 对象列表
     */
    List<RolePermission> queryByExample(RolePermission rolePermission);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param rolePermission 实例对象
     * @param page           查询起始位置
     * @param limit          查询条数
     * @return 对象列表
     */
    Pager<RolePermission> queryByExampleAndPage(RolePermission rolePermission, int page, int limit);

    /**
     * 新增数据
     *
     * @param rolePermission 实例对象
     * @return 实例对象
     */
    RolePermission insert(RolePermission rolePermission);

    /**
     * 新增数据 可以有空字段
     *
     * @param rolePermission 实例对象
     * @return 实例对象
     */
    RolePermission insertSelective(RolePermission rolePermission);

    /**
     * 修改数据
     *
     * @param rolePermission 实例对象
     * @return 实例对象
     */
    RolePermission update(RolePermission rolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}