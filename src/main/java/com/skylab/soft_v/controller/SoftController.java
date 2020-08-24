package com.skylab.soft_v.controller;

import com.skylab.soft_v.bean.SoftVO;
import cn.hutool.core.lang.UUID;
import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Const;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.component.ActionLog;
import com.skylab.soft_v.entity.Soft;
import com.skylab.soft_v.entity.User;
import com.skylab.soft_v.service.SoftService;
import com.skylab.soft_v.service.UserService;
import com.skylab.soft_v.util.JwtTokenUtil;
import com.skylab.soft_v.util.ReflectHelper;
import com.skylab.soft_v.util.SoulPage;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Security;
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
    @Resource
    private UserService userService;
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
    public ResultBean<Soft> add(Soft soft,@RequestParam(value = "file",required = false) MultipartFile file){
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
        if (example == null){
            return ResultBean.error("修改的软件不存在");
        }
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

    /**
     * 根据条件查询
     *
     * @param soft 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    @RequiresPermissions("soft_select")
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
    @RequiresPermissions("soft_select")
    public ResultBean<Pager<Soft>> queryByExampleAndPage(Soft soft, int page, int limit) {
        Pager<Soft> pager = softService.queryByExampleAndPage(soft, page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询软件列表
     * @param soulPage soultable 包装类
     * @param softVO 查询条件
     * @return SoulPage
     */
    @PostMapping("queryForSoulpage")
    @RequiresPermissions("soft_select")
    public Object queryForSoulpage(SoulPage<SoftVO> soulPage, SoftVO softVO)  {
        soulPage.setObj(softVO);
        return softService.queryForSoulpage(soulPage);
    }

    /**
     * 查询当前用户上传的软件列表
     * @param soulPage
     * @param request
     * @return
     */
    @PostMapping("queryByUserForSoulpage")
    @RequiresPermissions("soft_select")
    public Object queryByUserForSoulpage(SoulPage<SoftVO> soulPage,HttpServletRequest request)  {
        String accessToken=request.getHeader(Const.ACCESS_TOKEN);
        String username = JwtTokenUtil.getUserId(accessToken);
        if(SecurityUtils.getSubject().hasRole("admin")){
            return softService.queryAllForSoulpage(soulPage);
        }else {
            User user = userService.queryByUsername(username);
            return softService.queryByUserForSoulpage(soulPage,user);
        }

    }
    //将softVO中使用到的扩展字段key替换成value
//    public SoftVO json2Java (SoftVO softVO){
//        softVO.setColumn1(ReflectHelper.getValueByFieldName());
//        return softVO;
//    }
}