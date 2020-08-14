package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
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
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<Model> queryById(int id) {
        Model model = modelService.queryById(id);
        return ResultBean.success(model);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    public ResultBean<Pager<Model>> pageList(int page, int limit) {
        Pager<Model> pager = modelService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<Model>> list() {
        List<Model> categories = modelService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     *
     * @param model 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
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
    public ResultBean<Model> update(Model model) {
        try {
            Model update = modelService.update(model);
            return ResultBean.success(update);
        } catch (Exception e) {
            throw new BusinessException(400,"修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param model 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<Model>> queryByExample(Model model) {
        List<Model> list = modelService.queryByExample(model);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param model 查询条件
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<Model>> queryByExampleAndPage(Model model, int page, int limit) {
        Pager<Model> pager = modelService.queryByExampleAndPage(model, page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 根据条件分页查询
     * @param page 当前页
     * @param limit 每页行数
     * @param model 模块
     * @return 响应数据
     */
    @GetMapping("pageListByExample")
    //@RequiresPermissions("data_select")
    public ResultBean<Pager<Model>> pageListByExample(int page, int limit,Model model){
        Pager<Model> pager = modelService.queryByExampleAndPage(model, page, limit);
        return ResultBean.success(pager);
    }

}