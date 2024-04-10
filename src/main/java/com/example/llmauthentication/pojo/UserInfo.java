package com.example.llmauthentication.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
public class UserInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer userId;

    @TableField("user_name")
    private String userName;


    private String pwd;

    private String email;

    private String status;

    private String flg;

    @TableField("last_login")
    private Date lastLogin;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}