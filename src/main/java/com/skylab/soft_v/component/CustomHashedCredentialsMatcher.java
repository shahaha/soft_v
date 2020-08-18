package com.skylab.soft_v.component;

import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Const;
import com.skylab.soft_v.common.JWTToken;
import com.skylab.soft_v.service.RedisService;
import com.skylab.soft_v.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CustomHashedCredentialsMatcher
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Slf4j
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {
    @Autowired
    private RedisService redisService;
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        JWTToken jwtToken= (JWTToken) token;
        String accessToken= (String) jwtToken.getCredentials();
        String userId= JwtTokenUtil.getUserId(accessToken);
        log.info("doCredentialsMatch....userId={}",userId);
        //判断用户是否被删除
        if(redisService.hasKey(Const.DELETED_USER_KEY+userId)){
            throw new BusinessException(400,"该账号已被删除，请联系系统管理员");
        }
        /**
         * 判断用户是否退出登录
         */
        if(redisService.hasKey(Const.JWT_ACCESS_TOKEN_BLACKLIST+accessToken)){
            throw new BusinessException(400,"用户未登录，请重新登录");
        }

        //校验token
        if(!JwtTokenUtil.validateToken(accessToken)){
            throw new BusinessException(400,"token失效,请刷新token");
        }
        /**
         * 判断用户是否被标记了
         */
        if(redisService.hasKey(Const.JWT_REFRESH_KEY+userId)){
            /**
             * 判断用户是否已经刷新过
             */
            if(redisService.getExpire(Const.JWT_REFRESH_KEY+userId, TimeUnit.MILLISECONDS)>JwtTokenUtil.getRemainingTime(accessToken)){
                throw new BusinessException(400,"token失效,请刷新token");
            }
        }

        return true;
    }
}
