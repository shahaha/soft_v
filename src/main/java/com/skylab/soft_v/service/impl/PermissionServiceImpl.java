package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Permission;
import com.skylab.soft_v.mapper.PermissionMapper;
import com.skylab.soft_v.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 权限表(Permission)表服务实现类
 *
 * @author xw
 * @since 2020-08-11 15:35:51
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Permission queryById(Integer id) {
        return this.permissionMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<Permission> queryAllByPage(int page, int limit) {
        Pager<Permission> pager = new Pager<Permission>();
        pager.setRows(this.permissionMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.permissionMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Permission> queryList() {
        return this.permissionMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param permission 实例对象
     * @return 对象列表
     */
    @Override
    public List<Permission> queryByExample(Permission permission) {
        return this.permissionMapper.queryByExample(permission);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param permission 实例对象
     * @param page       查询起始位置
     * @param limit      查询条数
     * @return 对象列表
     */
    public Pager<Permission> queryByExampleAndPage(Permission permission, int page, int limit) {
        List<Permission> list = permissionMapper.queryByExample(permission);
        Pager<Permission> pager = new Pager<Permission>();
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
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission insert(Permission permission) {
        this.permissionMapper.insert(permission);
        return permission;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission insertSelective(Permission permission) {
        this.permissionMapper.insertSelective(permission);
        return permission;
    }

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission update(Permission permission) {
        this.permissionMapper.update(permission);
        return this.queryById(permission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.permissionMapper.deleteById(id) > 0;
    }

    /**
     * 通过角色Id获取权限列表
     *
     * @param id 角色Id
     * @return 权限列表
     */
    @Override
    public Set<Permission> queryByRoleId(Integer id) {
        return permissionMapper.queryByRoleId(id);
    }
}