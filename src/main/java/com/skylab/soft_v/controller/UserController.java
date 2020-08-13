package com.skylab.soft_v.controller;

import com.skylab.soft_v.bean.UserVO;
import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Const;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.component.ActionLog;
import com.skylab.soft_v.entity.Permission;
import com.skylab.soft_v.entity.Role;
import com.skylab.soft_v.entity.User;
import com.skylab.soft_v.service.PermissionService;
import com.skylab.soft_v.service.RedisService;
import com.skylab.soft_v.service.RoleService;
import com.skylab.soft_v.service.UserService;
import com.skylab.soft_v.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<User> queryById(int id) {
        User user = userService.queryById(id);
        return ResultBean.success(user);
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 响应数据
     */
    @PostMapping(value = "login")
    @ActionLog("用户登录")
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

    @GetMapping("/logout")
    @ApiOperation(value = "用户推出登录")
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

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    public ResultBean<Pager<User>> pageList(int page, int limit) {
        Pager<User> pager = userService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<User>> list() {
        List<User> categories = userService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     *
     * @param user 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    public ResultBean<User> add(User user) {
        try {
            User insertSelective = userService.insertSelective(user);
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
    public ResultBean<User> delete(Integer id) {
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
    public ResultBean<User> update(User user) {
        try {
            User update = userService.update(user);
            return ResultBean.success(update);
        } catch (Exception e) {
            return ResultBean.error("修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param user 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<User>> queryByExample(User user) {
        List<User> list = userService.queryByExample(user);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param user  查询条件
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<User>> queryByExampleAndPage(User user, int page, int limit) {
        Pager<User> pager = userService.queryByExampleAndPage(user, page, limit);
        return ResultBean.success(pager);
    }

}