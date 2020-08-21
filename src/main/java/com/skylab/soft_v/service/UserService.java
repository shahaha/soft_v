package com.skylab.soft_v.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skylab.soft_v.bean.AccountVO;
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
     * @param roleIds
     * @return 实例对象
     */
    User insertSelective(User user, List<Integer> roleIds);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @param roles
     * @return 实例对象
     */
    User update(User user, List<Integer> roles);

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

    Object dataGrid(SoulPage<User> soulPage) throws JsonProcessingException;
    /**
     * 根据条件查询用户及角色列表
     * @param msg 条件
     * @return 对象列表
     */
    List<AccountVO> queryAccountByMsg(String msg);

    /**
     * 判断用户是否在使用
     * @param id 用户id
     * @return 是否使用
     */
    boolean inUser(Integer id);

    /**
     * 修改密码
     * @param passwordOld 老密码
     * @param passwordNew 新密码
     * @param accessToken accessToken
     * @param refresgToken refresgToken
     */
    void userUpdatePwd(String passwordOld, String passwordNew, String accessToken, String refresgToken);

    /**
     * 根据角色名查询用户列表
     * @param role 角色
     * @return 用户列表
     */
    List<User> queryByRole(String role);
}