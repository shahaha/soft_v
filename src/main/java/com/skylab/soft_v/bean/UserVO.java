package com.skylab.soft_v.bean;

import com.skylab.soft_v.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
public class UserVO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 正常的业务token
     */
    private String accessToken;
    /**
     * 刷新token
     */
    private String refreshToken;
}
