package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Role;
import com.skylab.soft_v.entity.RolePermission;
import com.skylab.soft_v.mapper.RoleMapper;
import com.skylab.soft_v.mapper.RolePermissionMapper;
import com.skylab.soft_v.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 角色表(Role)表服务实现类
 *
 * @author xw
 * @since 2020-08-11 15:35:48
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Integer id) {
        return this.roleMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<Role> queryAllByPage(int page, int limit) {
        Pager<Role> pager = new Pager<Role>();
        pager.setRows(this.roleMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.roleMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Role> queryList() {
        return this.roleMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param role 实例对象
     * @return 对象列表
     */
    @Override
    public List<Role> queryByExample(Role role) {
        return this.roleMapper.queryByExample(role);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param role  实例对象
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public Pager<Role> queryByExampleAndPage(Role role, int page, int limit) {
        List<Role> list = roleMapper.queryByExample(role);
        Pager<Role> pager = new Pager<Role>();
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
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
        this.roleMapper.insert(role);
        return role;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param role 实例对象
     * @param permissionIds
     * @return 实例对象
     */
    @Override
    @Transactional
    public Role insertSelective(Role role, List<Integer> permissionIds) {
        this.roleMapper.insertSelective(role);
        for (Integer permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPId(permissionId);
            rolePermission.setRoleId(role.getId());
            rolePermissionMapper.insertSelective(rolePermission);
        }
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @param permissionIds
     * @return 实例对象
     */
    @Override
    public Role update(Role role, List<Integer> permissionIds) {
        this.roleMapper.update(role);
        rolePermissionMapper.deleteByRoleId(role.getId());
        for (Integer permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPId(permissionId);
            rolePermission.setRoleId(role.getId());
            rolePermissionMapper.insertSelective(rolePermission);
        }
        return this.queryById(role.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        rolePermissionMapper.deleteByRoleId(id);
        return this.roleMapper.deleteById(id) > 0;
    }

    /**
     * 通过用户Id查询角色列表
     *
     * @param userId 用户Id
     * @return 对象列表
     */
    @Override
    public Set<Role> queryByUserId(Integer userId) {
        return roleMapper.queryByUserId(userId);
    }

    /**
     * 判断角色是否在使用
     *
     * @param id 角色id
     * @return 是否使用
     */
    @Override
    public boolean inUser(Integer id) {
        return roleMapper.inUser(id) > 0;
    }
}