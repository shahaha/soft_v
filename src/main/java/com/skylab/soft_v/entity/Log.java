package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志表(Log)实体类
 *
 * @author xw
 * @since 2020-08-11 15:35:52
 */
@Data
public class Log implements Serializable {
    private static final long serialVersionUID = -75414600790078983L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 操作人
     */
    private String realName;
    /**
     * 操作时间
     */
    private Date time;
    /**
     * 主机ip
     */
    private String ip;
    /**
     * 调用方法
     */
    private String method;
    /**
     * 操作简述
     */
    private String des;


}