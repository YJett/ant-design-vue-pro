package com.example.llmauthentication.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
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
    @ExcelProperty(value = "学校名",index = 0)
    @TableField("schName")
    private String schName;
    @ExcelProperty(value = "学校地址",index = 1)
    private String address;
    @ExcelProperty(value = "邮编",index = 2)
    private String zipcode;
    @ExcelProperty(value = "邮箱",index = 4)
    private String email;
    @ExcelProperty(value = "联系人",index = 3)
    private String contact;
    @ExcelProperty(value = "电话",index = 5)
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