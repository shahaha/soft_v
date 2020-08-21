package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.RolePermission;
import com.skylab.soft_v.mapper.RolePermissionMapper;
import com.skylab.soft_v.service.RolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色_权限表(RolePermission)表服务实现类
 *
 * @author xw
 * @since 2020-08-11 15:35:48
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RolePermission queryById(Integer id) {
        return this.rolePermissionMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<RolePermission> queryAllByPage(int page, int limit) {
        Pager<RolePermission> pager = new Pager<RolePermission>();
        pager.setRows(this.rolePermissionMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.rolePermissionMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<RolePermission> queryList() {
        return this.rolePermissionMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param rolePermission 实例对象
     * @return 对象列表
     */
    @Override
    public List<RolePermission> queryByExample(RolePermission rolePermission) {
        return this.rolePermissionMapper.queryByExample(rolePermission);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param rolePermission 实例对象
     * @param page           查询起始位置
     * @param limit          查询条数
     * @return 对象列表
     */
    public Pager<RolePermission> queryByExampleAndPage(RolePermission rolePermission, int page, int limit) {
        List<RolePermission> list = rolePermissionMapper.queryByExample(rolePermission);
        Pager<RolePermission> pager = new Pager<RolePermission>();
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
     * @param rolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public RolePermission insert(RolePermission rolePermission) {
        this.rolePermissionMapper.insert(rolePermission);
        return rolePermission;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param rolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public RolePermission insertSelective(RolePermission rolePermission) {
        this.rolePermissionMapper.insertSelective(rolePermission);
        return rolePermission;
    }

    /**
     * 修改数据
     *
     * @param rolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public RolePermission update(RolePermission rolePermission) {
        this.rolePermissionMapper.update(rolePermission);
        return this.queryById(rolePermission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.rolePermissionMapper.deleteById(id) > 0;
    }
}