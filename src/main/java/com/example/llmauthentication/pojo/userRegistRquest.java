package com.example.llmauthentication.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
public class userRegistRquest implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer userId;

    @TableField("user_name")
    private String userName;


    private String pwd;

    private String email;





    private static final long serialVersionUID = 1L;
}