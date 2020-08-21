package com.skylab.soft_v.controller;

import com.skylab.soft_v.bean.SoftToolVO;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.component.ActionLog;
import com.skylab.soft_v.entity.SoftTool;
import com.skylab.soft_v.service.SoftToolService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
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

    @Value("${filepath}")
    private String filepath;

    /**
     * 添加记录
     *
     * @param softTool 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    @RequiresPermissions("softTool_add")
    @ActionLog("添加一个软件工具")
    public ResultBean<SoftTool> add(SoftTool softTool,@RequestParam(value = "file",required = false) MultipartFile file) {
        if (file == null || file.isEmpty()){
            return ResultBean.error("文件不能为空！");
        }
        if (StrUtil.isBlank(softTool.getName())){
            return ResultBean.error("工具名不能为空！");
        }
        SoftTool example = new SoftTool();
        example.setName(softTool.getName());
        List<SoftTool> exist = softToolService.queryByExample(example);
        if (!exist.isEmpty()){
            return ResultBean.error("该工具名已存在！");
        }
        try {
            String fileName = file.getOriginalFilename();
            fileName = UUID.randomUUID() + "-" + fileName;
            File dest = new File((filepath + fileName));
            if (!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            softTool.setAddress(fileName);
            SoftTool insertSelective = softToolService.insertSelective(softTool);
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
    @RequiresPermissions("softTool_delete")
    @ActionLog("删除一个软件工具")
    public ResultBean<SoftTool> delete(Integer id) {
        if (softToolService.inUser(id)){
            return ResultBean.error("工具正在使用，不能删除！");
        }
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
    @RequiresPermissions("softTool_update")
    @ActionLog("修改一个软件工具")
    public ResultBean<SoftTool> update(SoftTool softTool,@RequestParam(value = "file",required = false) MultipartFile file) {
        if (softTool.getId() == null){
            return ResultBean.error("id不能为空");
        }
        SoftTool exist = softToolService.queryById(softTool.getId());
        if(exist == null){
            return ResultBean.error("修改的软件工具不存在");
        }
        if (!exist.getType().equals(softTool.getType())){
            return ResultBean.error("工具类型不能修改");
        }
        if (file != null && !file.isEmpty()){
            String fileName = file.getOriginalFilename();
            fileName = UUID.randomUUID() + "-" + fileName;
            File dest = new File((filepath + fileName));
            if (!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                softTool.setAddress(fileName);
            }catch (IOException e){
                throw new BusinessException(400,"文件上传失败");
            }
        }
        try {
            SoftTool update = softToolService.update(softTool);
            return ResultBean.success(update);
        } catch (Exception e) {
            throw new BusinessException(400,"修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param softTool 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    @RequiresPermissions("softTool_select")
    public ResultBean<List<SoftTool>> queryByExample(SoftTool softTool) {
        List<SoftTool> list = softToolService.queryByExample(softTool);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param toolName 查询条件
     * @param page     当前页
     * @param limit    每页行数
     * @return 响应数据
     */
    @GetMapping("queryByNameAndPage")
    @RequiresPermissions("softTool_select")
    public ResultBean<Pager<SoftTool>> queryByNameAndPage(String toolName, int page, int limit) {
        Pager<SoftTool> pager = softToolService.queryByNameAndPage(toolName, page, limit);
        return ResultBean.success(pager);
    }
    /**
     * 查询所有记录
     *包含软件类型描述
     * @return 响应数据
     */
    @GetMapping("list2SoftToolVO")
    @RequiresPermissions("softTool_select")
    public ResultBean<List<SoftToolVO>> list2SoftToolVO(String tools) {
        if(tools.isEmpty()){
            return ResultBean.error("无数据");
        }
        List<SoftToolVO> categories = softToolService.queryList2SoftToolVO(tools);
        return ResultBean.success(categories);
    }
}