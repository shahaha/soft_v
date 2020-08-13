package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 字节大小(ByteSize)实体类
 *
 * @author xw
 * @since 2020-08-11 15:34:46
 */
@Data
public class ByteSize implements Serializable {
    private static final long serialVersionUID = 664629408695335320L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 大小
     */
    private String size;


}