package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 产品表(Product)实体类
 *
 * @author xw
 * @since 2020-08-12 18:07:47
 */
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = -98738388887131414L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 产品名
     */
    private String name;
    /**
     * 包含软件列表
     */
    private String softList;
    /**
     * 版本
     */
    private String version;
    /**
     * 客户
     */
    private String customer;


}