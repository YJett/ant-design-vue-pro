package com.example.llmauthentication.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
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

    @TableId(value = "comId",type = IdType.AUTO)
    private Integer comId;
    @ExcelProperty(value = "企业名",index = 0)
    @TableField("comName")
    private String comName;
    @ExcelProperty(value = "企业地址",index = 1)
    private String address;
    @ExcelProperty(value = "邮编",index = 2)
    private String zipcode;
    @ExcelProperty(value = "邮箱",index = 4)
    private String email;
    @ExcelProperty(value = "联系人",index = 3)
    private String contact;
    @ExcelProperty(value = "电话",index = 5)
    private String tel;

    private String filepath;

    private String login_name;

    private String comment;

    @TableField("createTime")
    private Date createTime;

    @TableField("updateTime")
    private Date updateTime;

    private String status;

    private static final long serialVersionUID = 1L;
}