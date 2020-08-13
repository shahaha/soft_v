package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.entity.RolePermission;
import com.skylab.soft_v.service.RolePermissionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色_权限表(RolePermission)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:22
 */
@RestController
@RequestMapping("rolePermission")
@Api(tags = "角色_权限表")
@Slf4j
public class RolePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<RolePermission> queryById(int id) {
        RolePermission rolePermission = rolePermissionService.queryById(id);
        return ResultBean.success(rolePermission);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    public ResultBean<Pager<RolePermission>> pageList(int page, int limit) {
        Pager<RolePermission> pager = rolePermissionService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<RolePermission>> list() {
        List<RolePermission> categories = rolePermissionService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     *
     * @param rolePermission 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    public ResultBean<RolePermission> add(RolePermission rolePermission) {
        try {
            RolePermission insertSelective = rolePermissionService.insertSelective(rolePermission);
            return ResultBean.success(insertSelective);
        } catch (Exception e) {
            return ResultBean.error("保存失败");
        }
    }

    /**
     * 删除一条记录
     *
     * @param id 主键
     * @return 响应数据
     */
    @PostMapping("delete")
    public ResultBean<RolePermission> delete(Integer id) {
        final boolean b = rolePermissionService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param rolePermission 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    public ResultBean<RolePermission> update(RolePermission rolePermission) {
        try {
            RolePermission update = rolePermissionService.update(rolePermission);
            return ResultBean.success(update);
        } catch (Exception e) {
            return ResultBean.error("修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param rolePermission 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<RolePermission>> queryByExample(RolePermission rolePermission) {
        List<RolePermission> list = rolePermissionService.queryByExample(rolePermission);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param rolePermission 查询条件
     * @param page           当前页
     * @param limit          每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<RolePermission>> queryByExampleAndPage(RolePermission rolePermission, int page, int limit) {
        Pager<RolePermission> pager = rolePermissionService.queryByExampleAndPage(rolePermission, page, limit);
        return ResultBean.success(pager);
    }

}