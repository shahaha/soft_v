package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.entity.ByteSize;
import com.skylab.soft_v.service.ByteSizeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<ByteSize> queryById(int id) {
        ByteSize byteSize = byteSizeService.queryById(id);
        return ResultBean.success(byteSize);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
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
    public ResultBean<ByteSize> update(ByteSize byteSize) {
        try {
            ByteSize update = byteSizeService.update(byteSize);
            return ResultBean.success(update);
        } catch (Exception e) {
            throw new BusinessException(400,"修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param byteSize 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<ByteSize>> queryByExample(ByteSize byteSize) {
        List<ByteSize> list = byteSizeService.queryByExample(byteSize);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param byteSize 查询条件
     * @param page     当前页
     * @param limit    每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<ByteSize>> queryByExampleAndPage(ByteSize byteSize, int page, int limit) {
        Pager<ByteSize> pager = byteSizeService.queryByExampleAndPage(byteSize, page, limit);
        return ResultBean.success(pager);
    }

}