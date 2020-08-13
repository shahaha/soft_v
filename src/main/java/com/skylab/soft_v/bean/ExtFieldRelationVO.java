package com.skylab.soft_v.bean;

import lombok.Data;

import java.util.List;

@Data
public class ExtFieldRelationVO {
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
     * 键值对
     */
    private List<JsonToObj> value;
    /**
     * 字段类型（text、select）
     */
    private String type;
    /**
     * 种类
     */
    private String category;
    /**
     * 是否为查询条件
     */
    private Boolean isTerm;
}