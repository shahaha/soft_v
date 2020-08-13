package com.skylab.soft_v.mapper;

import com.skylab.soft_v.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * 角色表(Role)表数据库访问层
 *
 * @author xw
 * @since 2020-08-11 15:35:50
 */
public interface RoleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Role> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param role 实例对象
     * @return 对象列表
     */
    List<Role> queryByExample(Role role);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Role> queryList();

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int insert(Role role);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int insertSelective(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int update(Role role);

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
     * 通过用户Id查询角色列表
     *
     * @param userId 用户Id
     * @return 对象列表
     */
    Set<Role> queryByUserId(Integer userId);

    /**
     * 判断角色是否被使用
     *
     * @param id 角色id
     * @return 是否使用
     */
    int inUser(Integer id);
}