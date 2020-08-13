package com.skylab.soft_v.service;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.UserRole;

import java.util.List;

/**
 * 用户_角色表(UserRole)表服务接口
 *
 * @author xw
 * @since 2020-08-11 15:35:54
 */
public interface UserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserRole queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<UserRole> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<UserRole> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param userRole 实例对象
     * @return 对象列表
     */
    List<UserRole> queryByExample(UserRole userRole);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param userRole 实例对象
     * @param page     查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    Pager<UserRole> queryByExampleAndPage(UserRole userRole, int page, int limit);

    /**
     * 新增数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    UserRole insert(UserRole userRole);

    /**
     * 新增数据 可以有空字段
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    UserRole insertSelective(UserRole userRole);

    /**
     * 修改数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    UserRole update(UserRole userRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}