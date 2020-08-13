package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.entity.UserRole;
import com.skylab.soft_v.service.UserRoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户_角色表(UserRole)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:30
 */
@RestController
@RequestMapping("userRole")
@Api(tags = "用户_角色表")
@Slf4j
public class UserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private UserRoleService userRoleService;

    /**
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<UserRole> queryById(int id) {
        UserRole userRole = userRoleService.queryById(id);
        return ResultBean.success(userRole);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    public ResultBean<Pager<UserRole>> pageList(int page, int limit) {
        Pager<UserRole> pager = userRoleService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<UserRole>> list() {
        List<UserRole> categories = userRoleService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     *
     * @param userRole 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    public ResultBean<UserRole> add(UserRole userRole) {
        try {
            UserRole insertSelective = userRoleService.insertSelective(userRole);
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
    public ResultBean<UserRole> delete(Integer id) {
        final boolean b = userRoleService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param userRole 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    public ResultBean<UserRole> update(UserRole userRole) {
        try {
            UserRole update = userRoleService.update(userRole);
            return ResultBean.success(update);
        } catch (Exception e) {
            return ResultBean.error("修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param userRole 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<UserRole>> queryByExample(UserRole userRole) {
        List<UserRole> list = userRoleService.queryByExample(userRole);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param userRole 查询条件
     * @param page     当前页
     * @param limit    每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<UserRole>> queryByExampleAndPage(UserRole userRole, int page, int limit) {
        Pager<UserRole> pager = userRoleService.queryByExampleAndPage(userRole, page, limit);
        return ResultBean.success(pager);
    }

}