package com.example.llmauthentication.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName company_info
 */
@Data
@TableName(value = "company_info")
public class ComInfoVo implements Serializable {


    private Integer comId;

    private String comName;

    private String address;

    private String zipcode;

    private String email;

    private String contact;

    private String tel;

    private String filepath;

    private String login_name;

    private String comment;

    private Date createTime;

    private Date updateTime;

    private String status;

    private String token;

    private static final long serialVersionUID = 1L;

    public ComInfoVo() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ComInfoVo;
    }

}