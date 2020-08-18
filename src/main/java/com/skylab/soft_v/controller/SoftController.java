package com.skylab.soft_v.controller;

import cn.hutool.core.lang.UUID;
import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.component.ActionLog;
import com.skylab.soft_v.entity.Soft;
import com.skylab.soft_v.service.SoftService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
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

    @Value("${filepath}")
    private String filepath;

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    @RequiresPermissions("soft_select")
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
    @RequiresPermissions("soft_select")
    public ResultBean<List<Soft>> list() {
        List<Soft> categories = softService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     * @param soft 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    @RequiresPermissions("soft_add")
    @ActionLog("添加一个软件并上传附件")
    public ResultBean<Soft> add(Soft soft,@RequestParam(value = "file") MultipartFile file){
        if (file == null || file.isEmpty()){
            return ResultBean.error("文件不能为空！");
        }
        if (soft.getCode() == null || "".equals(soft.getCode())){
            return ResultBean.error("编码不能为空");
        }
        Soft exist = softService.queryByCode(soft.getCode());
        if (exist != null){
            return ResultBean.error("该编码已存在，请更换编码！");
        }
        String fileName = file.getOriginalFilename();
        fileName = UUID.randomUUID() + "-" + fileName;
        File dest = new File((filepath + fileName));
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            soft.setAddress(fileName);
            soft.setUploadDate(new Date());
            Soft insertSelective = softService.insertSelective(soft);
            return ResultBean.success(insertSelective);
        }catch (IOException e){
            throw new BusinessException(400,"文件上传失败");
        }catch (Exception e){
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
    @RequiresPermissions("soft_delete")
    @ActionLog("删除一个软件")
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
     * @param soft 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    @RequiresPermissions("soft_update")
    @ActionLog("修改一个软件")
    public ResultBean<Soft> update(Soft soft,@RequestParam(value = "file",required = false) MultipartFile file){
        if (soft.getId() == null){
            return ResultBean.error("id不能为空");
        }
        Soft example = softService.queryById(soft.getId());
        if (!example.getCategory().equals(soft.getCategory())){
            return ResultBean.error("软件类型不能修改！");
        }
        if (!example.getCode().equals(soft.getCode())){
            return ResultBean.error("软件编码不能修改！");
        }
        if (!example.getEngineer().equals(soft.getEngineer())){
            return ResultBean.error("工程师不能修改！");
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
                soft.setAddress(fileName);
            }catch (IOException e){
                throw new BusinessException(400,"文件上传失败");
            }
        }
        try {
            Soft update = softService.update(soft);
            return ResultBean.success(update);
        }catch (Exception e){
            throw new BusinessException(400,"修改失败");
        }
    }

}