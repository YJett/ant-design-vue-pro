package com.example.llmauthentication.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
public class UserInfoVo implements Serializable {


    @JsonProperty("user_id")
    private Integer userId;

    private String userName;

    private String pwd;

    private String email;

    private String status;

    private String flg;

    private Date lastLogin;

    private Date createTime;

    private Date updateTime;

    private String token;

    private static final long serialVersionUID = 1L;
}