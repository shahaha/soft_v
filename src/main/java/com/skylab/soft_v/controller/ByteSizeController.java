package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.component.ActionLog;
import com.skylab.soft_v.entity.ByteSize;
import com.skylab.soft_v.service.ByteSizeService;
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
 * 字节大小(ByteSize)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:19
 */
@RestController
@RequestMapping("byteSize")
@Api(tags = "字节大小")
@Slf4j
public class ByteSizeController {
    /**
     * 服务对象
     */
    @Resource
    private ByteSizeService byteSizeService;

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    @RequiresPermissions("data_select")
    public ResultBean<Pager<ByteSize>> pageList(int page, int limit) {
        Pager<ByteSize> pager = byteSizeService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    @RequiresPermissions("data_select")
    public ResultBean<List<ByteSize>> list() {
        List<ByteSize> byteSizes = byteSizeService.queryList();
        return ResultBean.success(byteSizes);
    }

    /**
     * 添加记录
     *
     * @param byteSize 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    @RequiresPermissions("data_add")
    @ActionLog("添加一个字节选项")
    public ResultBean<ByteSize> add(ByteSize byteSize) {
        try {
            ByteSize insertSelective = byteSizeService.insertSelective(byteSize);
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
    @ActionLog("删除一个字节选项")
    public ResultBean<ByteSize> delete(Integer id) {
        final boolean b = byteSizeService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param byteSize 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    @RequiresPermissions("data_update")
    @ActionLog("修改一个字节选项")
    public ResultBean<ByteSize> update(ByteSize byteSize) {
        try {
            ByteSize update = byteSizeService.update(byteSize);
            return ResultBean.success(update);
        } catch (Exception e) {
            throw new BusinessException(400,"修改失败");
        }
    }

}