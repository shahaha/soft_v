package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.entity.Log;
import com.skylab.soft_v.service.LogService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 日志表(Log)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:27
 */
@RestController
@RequestMapping("log")
@Api(tags = "日志表")
@Slf4j
public class LogController {
    /**
     * 服务对象
     */
    @Resource
    private LogService logService;

    /**
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<Log> queryById(int id) {
        Log log = logService.queryById(id);
        return ResultBean.success(log);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    public ResultBean<Pager<Log>> pageList(int page, int limit) {
        Pager<Log> pager = logService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<Log>> list() {
        List<Log> categories = logService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     *
     * @param log 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    public ResultBean<Log> add(Log log) {
        try {
            Log insertSelective = logService.insertSelective(log);
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
    public ResultBean<Log> delete(Integer id) {
        final boolean b = logService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param log 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    public ResultBean<Log> update(Log log) {
        try {
            Log update = logService.update(log);
            return ResultBean.success(update);
        } catch (Exception e) {
            return ResultBean.error("修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param log 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<Log>> queryByExample(Log log) {
        List<Log> list = logService.queryByExample(log);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param log   查询条件
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<Log>> queryByExampleAndPage(Log log, int page, int limit) {
        Pager<Log> pager = logService.queryByExampleAndPage(log, page, limit);
        return ResultBean.success(pager);
    }

}