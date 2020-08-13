package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 芯片表(Chip)实体类
 *
 * @author xw
 * @since 2020-08-11 15:35:53
 */
@Data
public class Chip implements Serializable {
    private static final long serialVersionUID = -39651842493465544L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 芯片
     */
    private String chipName;


}