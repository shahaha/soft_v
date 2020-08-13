package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 型号表(Model)实体类
 *
 * @author xw
 * @since 2020-08-11 15:35:50
 */
@Data
public class Model implements Serializable {
    private static final long serialVersionUID = 734543824420669964L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 型号名
     */
    private String modelName;


}