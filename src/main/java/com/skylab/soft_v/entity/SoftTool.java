package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 软件工具表（测试、烧录工具）(SoftTool)实体类
 *
 * @author xw
 * @since 2020-08-12 18:07:50
 */
@Data
public class SoftTool implements Serializable {
    private static final long serialVersionUID = 645194137870218903L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 工具类型（烧录，测试...）
     */
    private Integer type;
    /**
     * 工具名
     */
    private String name;
    /**
     * 附件地址
     */
    private String address;


}