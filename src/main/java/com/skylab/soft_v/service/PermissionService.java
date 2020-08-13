package com.skylab.soft_v.service;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Permission;

import java.util.List;
import java.util.Set;

/**
 * 权限表(Permission)表服务接口
 *
 * @author xw
 * @since 2020-08-11 15:35:51
 */
public interface PermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<Permission> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Permission> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param permission 实例对象
     * @return 对象列表
     */
    List<Permission> queryByExample(Permission permission);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param permission 实例对象
     * @param page       查询起始位置
     * @param limit      查询条数
     * @return 对象列表
     */
    Pager<Permission> queryByExampleAndPage(Permission permission, int page, int limit);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission insert(Permission permission);

    /**
     * 新增数据 可以有空字段
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission insertSelective(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过角色Id获取权限列表
     * @param id 角色Id
     * @return 权限列表
     */
    Set<Permission> queryByRoleId(Integer id);
}