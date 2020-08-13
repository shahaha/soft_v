package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 软件工具类型表(ToolType)实体类
 *
 * @author xw
 * @since 2020-08-12 18:07:51
 */
@Data
public class ToolType implements Serializable {
    private static final long serialVersionUID = 380870100500583599L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 类型
     */
    private String type;


}