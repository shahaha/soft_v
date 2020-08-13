package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色_权限表(RolePermission)实体类
 *
 * @author xw
 * @since 2020-08-11 15:35:47
 */
@Data
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 630960545262904279L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 权限id
     */
    private Integer pId;


}