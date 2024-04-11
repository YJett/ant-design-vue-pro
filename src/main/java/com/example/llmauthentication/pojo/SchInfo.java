package com.example.llmauthentication.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName school_info
 */
@TableName(value ="school_info")
@Data
public class SchInfo implements Serializable {

    @TableId(value = "schId",type = IdType.AUTO)
    private Integer schId;

    @TableField("schName")
    private String schName;

    private String address;

    private String zipcode;

    private String email;

    private String contact;

    private String tel;

    private String login_name;

    private String comment;

    @TableField("createTime")
    private Date createTime;

    @TableField("updateTime")
    private Date updateTime;

    private String status;

    private static final long serialVersionUID = 1L;
}