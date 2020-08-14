package com.skylab.soft_v.controller;

import com.skylab.soft_v.bean.Tree;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.entity.Permission;
import com.skylab.soft_v.service.PermissionService;
import com.skylab.soft_v.util.TreeUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
     * 通过角色id 获取权限集合
     * @param roleId 角色id
     * @return 响应数据
     */
    @GetMapping("queryByRoleId")
    public ResultBean<?> queryByRoleId(Integer roleId){
        Set<Permission> permission = permissionService.queryByRoleId(roleId);
        return ResultBean.success(permission);
    }

    /**
     * 查询所有记录
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<Tree>> list(){
        List<Permission> permissions = permissionService.queryList();
        List<Tree> trees = new ArrayList<>();
        for (Permission permission : permissions){
            Tree tree = new Tree();
            tree.setId(permission.getId());
            tree.setParentId(permission.getParentId());
            tree.setTitle(permission.getRemark());
            trees.add(tree);
        }
        List<Tree> tree = TreeUtil.build(trees);
        return ResultBean.success("查询成功",tree);
    }

}