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
     * 根据条件查询并分页
     *
     * @param conditions   查询条件
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<Log>> queryByExampleAndPage(String conditions, int page, int limit) {
        Pager<Log> pager = logService.queryByExampleAndPage(conditions, page, limit);
        return ResultBean.success(pager);
    }

}