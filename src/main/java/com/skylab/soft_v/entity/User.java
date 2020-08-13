package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户表(User)实体类
 *
 * @author xw
 * @since 2020-08-11 15:35:53
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -56351439548418310L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String realName;


}