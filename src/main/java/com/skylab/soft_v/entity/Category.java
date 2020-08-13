package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 种类表（蓝牙、GNSS...）(Category)实体类
 *
 * @author xw
 * @since 2020-08-11 15:34:52
 */
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = 923496350267182059L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 种类
     */
    private String categoryName;


}