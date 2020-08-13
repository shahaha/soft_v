package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.entity.Chip;
import com.skylab.soft_v.service.ChipService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 芯片表(Chip)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:28
 */
@RestController
@RequestMapping("chip")
@Api(tags = "芯片表")
@Slf4j
public class ChipController {
    /**
     * 服务对象
     */
    @Resource
    private ChipService chipService;

    /**
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<Chip> queryById(int id) {
        Chip chip = chipService.queryById(id);
        return ResultBean.success(chip);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    public ResultBean<Pager<Chip>> pageList(int page, int limit) {
        Pager<Chip> pager = chipService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<Chip>> list() {
        List<Chip> categories = chipService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     *
     * @param chip 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    public ResultBean<Chip> add(Chip chip) {
        try {
            Chip insertSelective = chipService.insertSelective(chip);
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
    public ResultBean<Chip> delete(Integer id) {
        final boolean b = chipService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param chip 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    public ResultBean<Chip> update(Chip chip) {
        try {
            Chip update = chipService.update(chip);
            return ResultBean.success(update);
        } catch (Exception e) {
            return ResultBean.error("修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param chip 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<Chip>> queryByExample(Chip chip) {
        List<Chip> list = chipService.queryByExample(chip);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param chip  查询条件
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<Chip>> queryByExampleAndPage(Chip chip, int page, int limit) {
        Pager<Chip> pager = chipService.queryByExampleAndPage(chip, page, limit);
        return ResultBean.success(pager);
    }

}