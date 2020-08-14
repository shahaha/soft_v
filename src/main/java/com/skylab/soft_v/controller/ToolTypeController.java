package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.entity.ToolType;
import com.skylab.soft_v.service.ToolTypeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 软件工具类型表(ToolType)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:29
 */
@RestController
@RequestMapping("toolType")
@Api(tags = "软件工具类型表")
@Slf4j
public class ToolTypeController {
    /**
     * 服务对象
     */
    @Resource
    private ToolTypeService toolTypeService;

    /**
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<ToolType> queryById(int id) {
        ToolType toolType = toolTypeService.queryById(id);
        return ResultBean.success(toolType);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    public ResultBean<Pager<ToolType>> pageList(int page, int limit) {
        Pager<ToolType> pager = toolTypeService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<ToolType>> list() {
        List<ToolType> toolTypes = toolTypeService.queryList();
        return ResultBean.success(toolTypes);
    }

    /**
     * 添加记录
     *
     * @param toolType 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    public ResultBean<ToolType> add(ToolType toolType) {
        try {
            ToolType insertSelective = toolTypeService.insertSelective(toolType);
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
    public ResultBean<ToolType> delete(Integer id) {
        final boolean b = toolTypeService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param toolType 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    public ResultBean<ToolType> update(ToolType toolType) {
        try {
            ToolType update = toolTypeService.update(toolType);
            return ResultBean.success(update);
        } catch (Exception e) {
            throw new BusinessException(400,"修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param toolType 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<ToolType>> queryByExample(ToolType toolType) {
        List<ToolType> list = toolTypeService.queryByExample(toolType);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param toolType 查询条件
     * @param page     当前页
     * @param limit    每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<ToolType>> queryByExampleAndPage(ToolType toolType, int page, int limit) {
        Pager<ToolType> pager = toolTypeService.queryByExampleAndPage(toolType, page, limit);
        return ResultBean.success(pager);
    }

}