package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 扩展字段关联表(ExtFieldRelation)实体类
 *
 * @author xw
 * @since 2020-08-12 18:07:46
 */
@Data
public class ExtFieldRelation implements Serializable {
    private static final long serialVersionUID = -20143656106472367L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 字段描述
     */
    private String fieldDes;
    /**
     * 种类id
     */
    private Integer category;
    /**
     * 键值对
     */
    private String value;
    /**
     * 是否为查询条件
     */
    private Boolean isTerm;


}