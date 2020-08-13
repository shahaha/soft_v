package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色表(Role)实体类
 *
 * @author xw
 * @since 2020-08-11 15:35:48
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 235495159403105058L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 备注
     */
    private String remark;


}