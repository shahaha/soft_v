package com.skylab.soft_v.common;

/**
 * @author lwc
 * @date 2020/7/9 11:39
 * 一些系统中的常量
 */
public class Const {
    /**
     * 用户权鉴缓存 key
     */
    public static final String IDENTIFY_CACHE_KEY="shiro-cache:com.skylab.soft_v.component.CustomShiroRealm.authorizationCache:";
    /**
     * 用户名称 key
     */
    public static final String JWT_USER_NAME="jwt-user-name-key";

    /**
     * 角色信息key
     */
    public static final String ROLES_INFOS_KEY="roles-infos-key";

    /**
     * 权限信息key
     */
    public static final String PERMISSIONS_INFOS_KEY="permissions-infos-key";

    /**
     * refresh_token 主动退出后加入黑名单 key
     */
    public static final String JWT_REFRESH_TOKEN_BLACKLIST="jwt-refresh-token-blacklist_";

    /**
     * access_token 主动退出后加入黑名单 key
     */
    public static final String JWT_ACCESS_TOKEN_BLACKLIST="jwt-access-token-blacklist_";

    /**
     * 正常token
     */
    public static final String ACCESS_TOKEN="authorization";
    /**
     * 刷新token
     */
    public static final String REFRESH_TOKEN="refreshToken";

    /**
     * 标记用户是否已经删除
     */
    public static final String DELETED_USER_KEY="deleted-user-key_";

    /**
     * 主动去刷新 token key(适用场景 比如修改了用户的角色/权限去刷新token)
     */
    public static final String JWT_REFRESH_KEY="jwt-refresh-key_";
    /**
     * 标记新的access_token
     */
    public static final String JWT_REFRESH_IDENTIFICATION="jwt-refresh-identification_";

}
