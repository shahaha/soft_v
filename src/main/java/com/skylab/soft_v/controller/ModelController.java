package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.component.ActionLog;
import com.skylab.soft_v.entity.Model;
import com.skylab.soft_v.service.ModelService;
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
 * 型号表(Model)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:26
 */
@RestController
@RequestMapping("model")
@Api(tags = "型号表")
@Slf4j
public class ModelController {
    /**
     * 服务对象
     */
    @Resource
    private ModelService modelService;

    /**
     * 添加记录
     *
     * @param model 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    @RequiresPermissions("data_add")
    @ActionLog("添加一个模块型号选项")
    public ResultBean<Model> add(Model model) {
        try {
            Model insertSelective = modelService.insertSelective(model);
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
    @ActionLog("删除一个模块型号选项")
    public ResultBean<Model> delete(Integer id) {
        final boolean b = modelService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param model 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    @RequiresPermissions("data_update")
    @ActionLog("修改一个模块型号选项")
    public ResultBean<Model> update(Model model) {
        try {
            Model update = modelService.update(model);
            return ResultBean.success(update);
        } catch (Exception e) {
            throw new BusinessException(400,"修改失败");
        }
    }

    /**
     * 根据条件分页查询
     * @param page 当前页
     * @param limit 每页行数
     * @param model 模块
     * @return 响应数据
     */
    @GetMapping("pageListByExample")
    @RequiresPermissions("data_select")
    public ResultBean<Pager<Model>> pageListByExample(int page, int limit,Model model){
        Pager<Model> pager = modelService.queryByExampleAndPage(model, page, limit);
        return ResultBean.success(pager);
    }

}