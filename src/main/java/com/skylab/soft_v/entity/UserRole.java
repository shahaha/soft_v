package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户_角色表(UserRole)实体类
 *
 * @author xw
 * @since 2020-08-11 15:35:54
 */
@Data
public class UserRole implements Serializable {
    private static final long serialVersionUID = 657145877497008868L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色id
     */
    private Integer roleId;


}