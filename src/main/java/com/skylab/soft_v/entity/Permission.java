package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限表(Permission)实体类
 *
 * @author xw
 * @since 2020-08-11 15:35:51
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 599502295307335387L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 权限名
     */
    private String pName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 父ID
     */
    private String parentId;


}