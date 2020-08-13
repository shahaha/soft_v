package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.entity.Soft;
import com.skylab.soft_v.service.SoftService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 软件表(Soft)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:24
 */
@RestController
@RequestMapping("soft")
@Api(tags = "软件表")
@Slf4j
public class SoftController {
    /**
     * 服务对象
     */
    @Resource
    private SoftService softService;

    /**
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<Soft> queryById(int id) {
        Soft soft = softService.queryById(id);
        return ResultBean.success(soft);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    public ResultBean<Pager<Soft>> pageList(int page, int limit) {
        Pager<Soft> pager = softService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<Soft>> list() {
        List<Soft> categories = softService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     *
     * @param soft 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    public ResultBean<Soft> add(Soft soft) {
        try {
            Soft insertSelective = softService.insertSelective(soft);
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
    public ResultBean<Soft> delete(Integer id) {
        final boolean b = softService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param soft 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    public ResultBean<Soft> update(Soft soft) {
        try {
            Soft update = softService.update(soft);
            return ResultBean.success(update);
        } catch (Exception e) {
            return ResultBean.error("修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param soft 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<Soft>> queryByExample(Soft soft) {
        List<Soft> list = softService.queryByExample(soft);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param soft  查询条件
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<Soft>> queryByExampleAndPage(Soft soft, int page, int limit) {
        Pager<Soft> pager = softService.queryByExampleAndPage(soft, page, limit);
        return ResultBean.success(pager);
    }

}