package com.skylab.soft_v.bean;

import com.skylab.soft_v.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class AccountVO {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 角色对象集合
     */
    private List<Role> roles;
}
