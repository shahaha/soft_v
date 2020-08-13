package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.User;
import com.skylab.soft_v.mapper.UserMapper;
import com.skylab.soft_v.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(User)表服务实现类
 *
 * @author xw
 * @since 2020-08-11 15:35:53
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<User> queryAllByPage(int page, int limit) {
        Pager<User> pager = new Pager<User>();
        pager.setRows(this.userMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.userMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<User> queryList() {
        return this.userMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    @Override
    public List<User> queryByExample(User user) {
        return this.userMapper.queryByExample(user);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param user  实例对象
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public Pager<User> queryByExampleAndPage(User user, int page, int limit) {
        List<User> list = userMapper.queryByExample(user);
        Pager<User> pager = new Pager<User>();
        int count = list.size();
        pager.setTotal(count);
        int fromIndex = (page - 1) * limit;
        int toIndex = fromIndex + limit;
        pager.setRows(list.subList(fromIndex, Math.min(toIndex, count)));
        return pager;
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userMapper.insert(user);
        return user;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insertSelective(User user) {
        this.userMapper.insertSelective(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userMapper.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userMapper.deleteById(id) > 0;
    }

    /**
     * 通过用户名查询
     *
     * @param username 用户名
     * @return 实例对象
     */
    @Override
    public User queryByUsername(String username) {
        return userMapper.queryByUsername(username);
    }
}