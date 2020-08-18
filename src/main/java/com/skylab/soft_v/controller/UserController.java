package com.skylab.soft_v.controller;

import cn.hutool.core.util.StrUtil;
import com.skylab.soft_v.bean.AccountVO;
import com.skylab.soft_v.bean.UserVO;
import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Const;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.component.ActionLog;
import com.skylab.soft_v.entity.Permission;
import com.skylab.soft_v.entity.Role;
import com.skylab.soft_v.entity.User;
import com.skylab.soft_v.entity.UserRole;
import com.skylab.soft_v.service.PermissionService;
import com.skylab.soft_v.service.RedisService;
import com.skylab.soft_v.service.RoleService;
import com.skylab.soft_v.service.UserService;
import com.skylab.soft_v.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用户表(User)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:28
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户表")
@Slf4j
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RedisService redisService;

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 响应数据
     */
    @PostMapping(value = "login")
    public ResultBean<UserVO> login(String username, String password){
        User user = userService.queryByUsername(username);
        //验证账号是否存在
        if(user == null){
            return ResultBean.error("账号无效");
        }
        if(!user.getPassword().equals(password)){
            return ResultBean.error("用戶名或密码错误");
        }
        UserVO loginUser = new UserVO();
        loginUser.setId(user.getId());
        loginUser.setRealName(user.getRealName());
        loginUser.setUsername(user.getUsername());
        Map<String, Object> claims=new HashMap<>();
        List<String> roleNames = new ArrayList<>();
        List<String> pNames = new ArrayList<>();
        Set<Role> roles = roleService.queryByUserId(user.getId());
        for (Role role : roles) {
            roleNames.add(role.getRoleName());
            Set<Permission> permissions = permissionService.queryByRoleId(role.getId());
            for (Permission permission : permissions) {
                pNames.add(permission.getPName());
            }
        }
        claims.put(Const.ROLES_INFOS_KEY,roleNames);
        claims.put(Const.PERMISSIONS_INFOS_KEY,pNames);
        claims.put(Const.JWT_USER_NAME,user.getUsername());
        String token = JwtTokenUtil.getAccessToken(user.getUsername(),claims);
        String refreshToken=JwtTokenUtil.getRefreshToken(user.getUsername(),claims);
        loginUser.setAccessToken(token);
        loginUser.setRefreshToken(refreshToken);
        return ResultBean.success("登录成功",loginUser);
    }

    @PostMapping("/logout")
    public ResultBean<User> logout(HttpServletRequest request){
        try {
            String accessToken=request.getHeader(Const.ACCESS_TOKEN);
            String refreshToken=request.getHeader(Const.REFRESH_TOKEN);
            if(StringUtils.isEmpty(accessToken)||StringUtils.isEmpty(refreshToken)){
                throw new BusinessException(500,"传入数据异常");
            }
            Subject subject = SecurityUtils.getSubject();
            if(subject!=null){
                subject.logout();
            }
            String userId = JwtTokenUtil.getUserId(accessToken);
            /**
             * 把accessToken 加入黑名单
             */
            redisService.set(Const.JWT_ACCESS_TOKEN_BLACKLIST+accessToken,userId,JwtTokenUtil.getRemainingTime(accessToken), TimeUnit.MILLISECONDS);
            /**
             * 把refreshToken 加入黑名单
             */
            redisService.set(Const.JWT_REFRESH_IDENTIFICATION+refreshToken,userId,JwtTokenUtil.getRemainingTime(refreshToken),TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("logout:{}",e);
        }
        return ResultBean.success();
    }

    @PostMapping("/getLoginUser")
    public ResultBean<User> getLoginUser(HttpServletRequest request){
        String accessToken=request.getHeader(Const.ACCESS_TOKEN);
        String username = JwtTokenUtil.getUserId(accessToken);
        User user = userService.queryByUsername(username);
        return ResultBean.success(user);
    }

    /**
     * 添加记录
     *
     * @param user 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    @RequiresPermissions("user_add")
    @ActionLog("添加一个用户")
    public ResultBean<User> add(User user,@RequestParam(defaultValue = "") String roleIds) {
        if (StrUtil.isBlank(user.getRealName()) || StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())){
            return ResultBean.error("用户信息不完整");
        }
        if (StrUtil.isBlank(roleIds)){
            return ResultBean.error("角色不能为空");
        }
        User exit = userService.queryByUsername(user.getUsername());
        if (exit != null){
           return ResultBean.error("该用户名已存在，无法添加");
        }
        try {
            String[] split = roleIds.split(",");
            List<Integer> roles = new ArrayList<>();
            for (String roleId : split) {
                Role role = roleService.queryById(Integer.parseInt(roleId));
                if (role == null) {
                    return ResultBean.error("不能添加不存在的角色");
                } else {
                    roles.add(Integer.parseInt(roleId));
                }
            }
            User insertSelective = userService.insertSelective(user,roles);
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
    @RequiresPermissions("user_delete")
    @ActionLog("删除一个用户")
    public ResultBean<User> delete(Integer id) {
        if (userService.inUser(id)){
            return ResultBean.error("用户正在使用，不能删除！");
        }
        final boolean b = userService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param user 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    @RequiresPermissions("user_update")
    @ActionLog("修改一个用户")
    public ResultBean<User> update(User user,@RequestParam(defaultValue = "") String roleIds) {
        if (user.getId() == null){
            return ResultBean.error("用户Id不存在");
        }
        if (StrUtil.isBlank(user.getRealName())){
            return ResultBean.error("用户信息不完整");
        }
        if (StrUtil.isBlank(roleIds)){
            return ResultBean.error("角色不能为空");
        }
        User exit = userService.queryByUsername(user.getUsername());
        if (exit != null && !exit.getId().equals(user.getId())){
            return ResultBean.error("该用户名已存在，无法添加");
        }
        try {
            String[] split = roleIds.split(",");
            List<Integer> roles = new ArrayList<>();
            for (String roleId : split) {
                Role role = roleService.queryById(Integer.parseInt(roleId));
                if (role == null) {
                    return ResultBean.error("不能添加不存在的角色");
                } else {
                    roles.add(Integer.parseInt(roleId));
                }
            }
            User update = userService.update(user,roles);
            return ResultBean.success(update);
        } catch (Exception e) {
            return ResultBean.error("修改失败");
        }
    }

    /**
     * 根据条件查询账号并分页
     *
     * @param msg   筛选条件 可为空、角色名、用户名
     * @param page  页码
     * @param limit 每页条数
     * @return 返回结果
     */
    @GetMapping("queryAccountByMsg")
    @RequiresPermissions("user_select")
    public ResultBean<?> queryAccountByMsg(@RequestParam(defaultValue = "") String msg, int page, int limit) {
        List<AccountVO> accountVOS = userService.queryAccountByMsg(msg);
        Pager<AccountVO> pager = new Pager<>();
        int count = accountVOS.size();
        pager.setTotal(count);
        int fromIndex = (page - 1) * limit;
        int toIndex = fromIndex + limit;
        pager.setRows(accountVOS.subList(fromIndex, Math.min(toIndex, count)));
        return ResultBean.success(pager);
    }

    @PostMapping("resetPassword")
    @ActionLog("修改密码")
    public ResultBean<User> updatePwd(String passwordOld, String passwordNew, HttpServletRequest request){
        if (StrUtil.isBlank(passwordOld)){
            return ResultBean.error("旧密码不能为空");
        }
        if (StrUtil.isBlank(passwordNew)){
            return ResultBean.error("新密码不能为空");
        }
        String accessToken=request.getHeader(Const.ACCESS_TOKEN);
        String refresgToken=request.getHeader(Const.REFRESH_TOKEN);
        userService.userUpdatePwd(passwordOld,passwordNew,accessToken,refresgToken);
        return ResultBean.success("修改成功");
    }

}