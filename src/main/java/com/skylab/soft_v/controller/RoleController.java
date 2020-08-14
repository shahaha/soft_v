package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.component.ActionLog;
import com.skylab.soft_v.entity.Permission;
import com.skylab.soft_v.entity.Role;
import com.skylab.soft_v.service.PermissionService;
import com.skylab.soft_v.service.RoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色表(Role)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:23
 */
@RestController
@RequestMapping("role")
@Api(tags = "角色表")
@Slf4j
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    /**
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<Role> queryById(int id) {
        Role role = roleService.queryById(id);
        return ResultBean.success(role);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    public ResultBean<Pager<Role>> pageList(int page, int limit) {
        Pager<Role> pager = roleService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<Role>> list() {
        List<Role> categories = roleService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     * @param role 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    @RequiresPermissions("role_add")
    @ActionLog("添加一个角色")
    public ResultBean<Role> add(Role role,String permissionIds){
        if (permissionIds == null || "".equals(permissionIds)){
            return ResultBean.error("权限列表不能为空！");
        }
        if (role.getRoleName() == null || "".equals(role.getRoleName())){
            return ResultBean.error("角色名不能为空！");
        }
        Role example = new Role();
        example.setRoleName(role.getRoleName());
        List<Role> exist = roleService.queryByExample(example);
        if (!exist.isEmpty()){
            return ResultBean.error("该角色名已存在！");
        }
        try {
            String[] split = permissionIds.split(",");
            List<Integer> permissions = new ArrayList<>();
            for (String permissionId : split) {
                Permission permission = permissionService.queryById(Integer.parseInt(permissionId));
                if (permission == null) {
                    return ResultBean.error("不能添加不存在的权限");
                } else {
                    permissions.add(Integer.parseInt(permissionId));
                }
            }
            Role insertSelective = roleService.insertSelective(role,permissions);
            return ResultBean.success(insertSelective);
        }catch (Exception e){
            throw new BusinessException(400,"保存失败");
        }
    }

    /**
     * 删除一条记录
     *
     * @param id 主键
     * @return 响应数据
     */
    @PostMapping("delete")
    public ResultBean<Role> delete(Integer id) {
        if (roleService.inUser(id)){
            return ResultBean.error("角色正在使用，不能删除！");
        }
        final boolean b = roleService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param role 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    public ResultBean<Role> update(Role role,String permissionIds) {
        if (role.getRoleName() == null || "".equals(role.getRoleName())){
            return ResultBean.error("角色名不能为空！");
        }
        if (permissionIds == null || "".equals(permissionIds)){
            return ResultBean.error("权限列表不能为空！");
        }
        if (role.getId() == null){
            return ResultBean.error("角色Id不存在！");
        }
        Role example = new Role();
        example.setRoleName(role.getRoleName());
        List<Role> exist = roleService.queryByExample(example);
        if (!exist.isEmpty() && !role.getId().equals(exist.get(0).getId())){
            return ResultBean.error("该角色名已存在！");
        }
        try {
            String[] split = permissionIds.split(",");
            List<Integer> permissions = new ArrayList<>();
            for (String permissionId : split) {
                Permission permission = permissionService.queryById(Integer.parseInt(permissionId));
                if (permission == null) {
                    return ResultBean.error("不能添加不存在的权限");
                } else {
                    permissions.add(Integer.parseInt(permissionId));
                }
            }
            Role update = roleService.update(role,permissions);
            return ResultBean.success("修改成功",update);
        }catch (Exception e){
            throw new BusinessException(400,"修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param role 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<Role>> queryByExample(Role role) {
        List<Role> list = roleService.queryByExample(role);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param role  查询条件
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<Role>> queryByExampleAndPage(Role role, int page, int limit) {
        Pager<Role> pager = roleService.queryByExampleAndPage(role, page, limit);
        return ResultBean.success(pager);
    }

}