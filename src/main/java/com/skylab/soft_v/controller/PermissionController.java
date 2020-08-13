package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.entity.Permission;
import com.skylab.soft_v.service.PermissionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(Permission)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:26
 */
@RestController
@RequestMapping("permission")
@Api(tags = "权限表")
@Slf4j
public class PermissionController {
    /**
     * 服务对象
     */
    @Resource
    private PermissionService permissionService;

    /**
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<Permission> queryById(int id) {
        Permission permission = permissionService.queryById(id);
        return ResultBean.success(permission);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    public ResultBean<Pager<Permission>> pageList(int page, int limit) {
        Pager<Permission> pager = permissionService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<Permission>> list() {
        List<Permission> categories = permissionService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     *
     * @param permission 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    public ResultBean<Permission> add(Permission permission) {
        try {
            Permission insertSelective = permissionService.insertSelective(permission);
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
    public ResultBean<Permission> delete(Integer id) {
        final boolean b = permissionService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param permission 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    public ResultBean<Permission> update(Permission permission) {
        try {
            Permission update = permissionService.update(permission);
            return ResultBean.success(update);
        } catch (Exception e) {
            return ResultBean.error("修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param permission 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<Permission>> queryByExample(Permission permission) {
        List<Permission> list = permissionService.queryByExample(permission);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param permission 查询条件
     * @param page       当前页
     * @param limit      每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<Permission>> queryByExampleAndPage(Permission permission, int page, int limit) {
        Pager<Permission> pager = permissionService.queryByExampleAndPage(permission, page, limit);
        return ResultBean.success(pager);
    }

}