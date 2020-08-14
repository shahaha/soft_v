package com.skylab.soft_v.mapper;

import com.skylab.soft_v.bean.AccountVO;
import com.skylab.soft_v.entity.User;

import java.util.List;

/**
 * 用户表(User)表数据库访问层
 *
 * @author xw
 * @since 2020-08-11 15:35:54
 */
public interface UserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryByExample(User user);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<User> queryList();

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insertSelective(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

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
     * 通过用户名查询
     *
     * @param username 用户名
     * @return 实例对象
     */
    User queryByUsername(String username);
    /**
     * 根据条件查询用户及角色列表
     *
     * @param msg 条件
     * @return 对象列表
     */
    List<AccountVO> queryAccountByMsg(String msg);

    /**
     * 判断用户是否在使用
     *
     * @param id 用户id
     * @return 是否使用
     */
    Integer inUser(Integer id);
}