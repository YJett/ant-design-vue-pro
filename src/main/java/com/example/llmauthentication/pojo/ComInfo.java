package com.example.llmauthentication.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName company_info
 */
@TableName(value ="company_info")
@Data
public class ComInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer comId;

    @TableField("comName")
    private String comName;

    private String address;

    private String zipcode;

    private String email;

    private String contact;

    private String tel;

    private String filepath;

    private String login_name;

    private String comment;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    private String status;

    private static final long serialVersionUID = 1L;
}