package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.UserRole;
import com.skylab.soft_v.mapper.UserRoleMapper;
import com.skylab.soft_v.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户_角色表(UserRole)表服务实现类
 *
 * @author xw
 * @since 2020-08-11 15:35:54
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserRole queryById(Integer id) {
        return this.userRoleMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<UserRole> queryAllByPage(int page, int limit) {
        Pager<UserRole> pager = new Pager<UserRole>();
        pager.setRows(this.userRoleMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.userRoleMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<UserRole> queryList() {
        return this.userRoleMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param userRole 实例对象
     * @return 对象列表
     */
    @Override
    public List<UserRole> queryByExample(UserRole userRole) {
        return this.userRoleMapper.queryByExample(userRole);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param userRole 实例对象
     * @param page     查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    public Pager<UserRole> queryByExampleAndPage(UserRole userRole, int page, int limit) {
        List<UserRole> list = userRoleMapper.queryByExample(userRole);
        Pager<UserRole> pager = new Pager<UserRole>();
        int count = list.size();
        pager.setTotal(count);
        page = Math.min(page,(count/limit)+1);
        int fromIndex = (page - 1) * limit;
        int toIndex = fromIndex + limit;
        pager.setRows(list.subList(fromIndex, Math.min(toIndex, count)));
        return pager;
    }

    /**
     * 新增数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole insert(UserRole userRole) {
        this.userRoleMapper.insert(userRole);
        return userRole;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole insertSelective(UserRole userRole) {
        this.userRoleMapper.insertSelective(userRole);
        return userRole;
    }

    /**
     * 修改数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole update(UserRole userRole) {
        this.userRoleMapper.update(userRole);
        return this.queryById(userRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userRoleMapper.deleteById(id) > 0;
    }
}