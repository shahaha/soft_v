package com.skylab.soft_v.service;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * 角色表(Role)表服务接口
 *
 * @author xw
 * @since 2020-08-11 15:35:48
 */
public interface RoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<Role> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Role> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param role 实例对象
     * @return 对象列表
     */
    List<Role> queryByExample(Role role);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param role  实例对象
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<Role> queryByExampleAndPage(Role role, int page, int limit);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insert(Role role);

    /**
     * 新增数据 可以有空字段
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insertSelective(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过用户Id查询角色列表
     * @param userId 用户Id
     * @return 对象列表
     */
    Set<Role> queryByUserId(Integer userId);
}