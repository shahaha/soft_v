package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.entity.ExtFieldRelation;
import com.skylab.soft_v.service.ExtFieldRelationService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 扩展字段关联表(ExtFieldRelation)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:25
 */
@RestController
@RequestMapping("extFieldRelation")
@Api(tags = "扩展字段关联表")
@Slf4j
public class ExtFieldRelationController {
    /**
     * 服务对象
     */
    @Resource
    private ExtFieldRelationService extFieldRelationService;

    /**
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<ExtFieldRelation> queryById(int id) {
        ExtFieldRelation extFieldRelation = extFieldRelationService.queryById(id);
        return ResultBean.success(extFieldRelation);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    public ResultBean<Pager<ExtFieldRelation>> pageList(int page, int limit) {
        Pager<ExtFieldRelation> pager = extFieldRelationService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<ExtFieldRelation>> list() {
        List<ExtFieldRelation> categories = extFieldRelationService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     *
     * @param extFieldRelation 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    public ResultBean<ExtFieldRelation> add(ExtFieldRelation extFieldRelation) {
        try {
            ExtFieldRelation insertSelective = extFieldRelationService.insertSelective(extFieldRelation);
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
    public ResultBean<ExtFieldRelation> delete(Integer id) {
        final boolean b = extFieldRelationService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param extFieldRelation 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    public ResultBean<ExtFieldRelation> update(ExtFieldRelation extFieldRelation) {
        try {
            ExtFieldRelation update = extFieldRelationService.update(extFieldRelation);
            return ResultBean.success(update);
        } catch (Exception e) {
            return ResultBean.error("修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param extFieldRelation 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<ExtFieldRelation>> queryByExample(ExtFieldRelation extFieldRelation) {
        List<ExtFieldRelation> list = extFieldRelationService.queryByExample(extFieldRelation);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param extFieldRelation 查询条件
     * @param page             当前页
     * @param limit            每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<ExtFieldRelation>> queryByExampleAndPage(ExtFieldRelation extFieldRelation, int page, int limit) {
        Pager<ExtFieldRelation> pager = extFieldRelationService.queryByExampleAndPage(extFieldRelation, page, limit);
        return ResultBean.success(pager);
    }

}