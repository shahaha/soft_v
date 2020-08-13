package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.entity.SoftTool;
import com.skylab.soft_v.service.SoftToolService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 软件工具表（测试、烧录工具）(SoftTool)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:22
 */
@RestController
@RequestMapping("softTool")
@Api(tags = "软件工具表（测试、烧录工具）")
@Slf4j
public class SoftToolController {
    /**
     * 服务对象
     */
    @Resource
    private SoftToolService softToolService;

    /**
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<SoftTool> queryById(int id) {
        SoftTool softTool = softToolService.queryById(id);
        return ResultBean.success(softTool);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    public ResultBean<Pager<SoftTool>> pageList(int page, int limit) {
        Pager<SoftTool> pager = softToolService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<SoftTool>> list() {
        List<SoftTool> categories = softToolService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     *
     * @param softTool 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    public ResultBean<SoftTool> add(SoftTool softTool) {
        try {
            SoftTool insertSelective = softToolService.insertSelective(softTool);
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
    public ResultBean<SoftTool> delete(Integer id) {
        final boolean b = softToolService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param softTool 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    public ResultBean<SoftTool> update(SoftTool softTool) {
        try {
            SoftTool update = softToolService.update(softTool);
            return ResultBean.success(update);
        } catch (Exception e) {
            return ResultBean.error("修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param softTool 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<SoftTool>> queryByExample(SoftTool softTool) {
        List<SoftTool> list = softToolService.queryByExample(softTool);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param softTool 查询条件
     * @param page     当前页
     * @param limit    每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<SoftTool>> queryByExampleAndPage(SoftTool softTool, int page, int limit) {
        Pager<SoftTool> pager = softToolService.queryByExampleAndPage(softTool, page, limit);
        return ResultBean.success(pager);
    }

}