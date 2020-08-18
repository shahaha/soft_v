package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.component.ActionLog;
import com.skylab.soft_v.entity.Category;
import com.skylab.soft_v.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 种类表（蓝牙、GNSS...）(Category)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:21
 */
@RestController
@RequestMapping("category")
@Api(tags = "种类表（蓝牙、GNSS...）")
@Slf4j
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    /**
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    @RequiresPermissions("data_select")
    public ResultBean<Category> queryById(int id) {
        Category category = categoryService.queryById(id);
        return ResultBean.success(category);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    @RequiresPermissions("data_select")
    public ResultBean<List<Category>> list() {
        List<Category> categories = categoryService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     *
     * @param category 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    @RequiresPermissions("data_add")
    @ActionLog("添加一个业务类型选项")
    public ResultBean<Category> add(Category category) {
        try {
            Category insertSelective = categoryService.insertSelective(category);
            return ResultBean.success(insertSelective);
        } catch (Exception e) {
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
    @RequiresPermissions("data_delete")
    @ActionLog("删除一个业务类型选项")
    public ResultBean<Category> delete(Integer id) {
        final boolean b = categoryService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            throw new BusinessException(400,"修改失败");
        }
    }

    /**
     * 更新一条记录
     *
     * @param category 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    @RequiresPermissions("data_update")
    @ActionLog("修改一个业务类型选项")
    public ResultBean<Category> update(Category category) {
        try {
            Category update = categoryService.update(category);
            return ResultBean.success(update);
        } catch (Exception e) {
            return ResultBean.error("修改失败");
        }
    }

}