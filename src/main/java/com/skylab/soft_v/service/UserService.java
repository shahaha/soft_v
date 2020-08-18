package com.skylab.soft_v.service;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.User;
import com.skylab.soft_v.util.SoulPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author xw
 * @since 2020-08-11 15:35:53
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<User> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<User> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryByExample(User user);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param user  实例对象
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<User> queryByExampleAndPage(User user, int page, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 新增数据 可以有空字段
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insertSelective(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过用户名查询
     * @param username 用户名
     * @return 实例对象
     */
    User queryByUsername(String username);

    Object dataGrid(SoulPage<User> soulPage);
}