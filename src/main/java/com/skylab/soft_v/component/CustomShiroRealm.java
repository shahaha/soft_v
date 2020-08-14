package com.skylab.soft_v.component;


import com.skylab.soft_v.common.Const;
import com.skylab.soft_v.common.JWTToken;
import com.skylab.soft_v.entity.Permission;
import com.skylab.soft_v.entity.Role;
import com.skylab.soft_v.entity.User;
import com.skylab.soft_v.service.PermissionService;
import com.skylab.soft_v.service.RedisService;
import com.skylab.soft_v.service.RoleService;
import com.skylab.soft_v.service.UserService;
import com.skylab.soft_v.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
//@Component
public class CustomShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RedisService redisService;

    /**
     * 设置支持令牌校验
     *
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 登录
     * 主要业务：
     * 当业务代码调用 subject.login(customPasswordToken); 方法后
     * 就会自动调用这个方法 验证用户名/密码
     * 这里我们改造成 验证 token 是否有效 已经自定义了 shiro 验证
     * @param authenticationToken authenticationToken
     * @return 身份验证信息
     * @throws AuthenticationException AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("======登录验证========");
        String token = (String) authenticationToken.getCredentials();
        return new SimpleAuthenticationInfo(token, token, getName());
    }

    /**
     * 主要业务：
     * 系统业务出现要验证用户的角色权限的时候，就会调用这个方法
     * 来获取该用户所拥有的角色/权限
     * 这个用户授权的方法我们可以缓存起来不用每次都调用这个方法。
     * 后续的课程我们会结合 redis 实现它
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("======授权========");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String accessToken= (String) principalCollection.getPrimaryPrincipal();
        Claims claimsFromToken = JwtTokenUtil.getClaimsFromToken(accessToken);
        String userId=JwtTokenUtil.getUserId(accessToken);
        User user = userService.queryByUsername(userId);
        log.info("userId={}",user.getId());
        if(redisService.hasKey(Const.JWT_REFRESH_KEY+userId)&&redisService.getExpire(Const.JWT_REFRESH_KEY+userId, TimeUnit.MILLISECONDS)>JwtTokenUtil.getRemainingTime(accessToken)){
            log.info("dssss");
            Set<Role> roles = roleService.queryByUserId(user.getId());
            for (Role role : roles){
                log.info(role.getRoleName());
                simpleAuthorizationInfo.addRole(role.getRoleName());
                Set<Permission> permissions = permissionService.queryByRoleId(role.getId());
                for (Permission permission : permissions){
                    simpleAuthorizationInfo.addStringPermission(permission.getPName());
                }
            }

        }else {
            log.info("eeeeeee");
            if(claimsFromToken.get(Const.PERMISSIONS_INFOS_KEY)!=null){
                simpleAuthorizationInfo.addStringPermissions((Collection<String>) claimsFromToken.get(Const.PERMISSIONS_INFOS_KEY));
            }
            if(claimsFromToken.get(Const.ROLES_INFOS_KEY)!=null){
                simpleAuthorizationInfo.addRoles((Collection<String>) claimsFromToken.get(Const.ROLES_INFOS_KEY));
            }
        }
        return simpleAuthorizationInfo;
    }

}
