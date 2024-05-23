package com.example.llmauthentication.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName certificate_info
 */
@TableName(value ="certificate_info")
@Data
public class CertificateInfo implements Serializable {
    /**
     * 学校ID
     */
    private Integer schid;

    /**
     * 学号
     */
    private String studentno;

    /**
     * 发证机构
     */
    private String institution;

    /**
     * 证书等级（国家级，省级，行业或部门级，企业级，其他
     */
    private String level;

    /**
     * 证书号
     */
    private String certiid;

    /**
     * 证书名称
     */
    private String certinm;

    /**
     * 发证日期（YYYYMM）
     */
    private String issueDate;

    /**
     * 是否支持电子证书（是，否）
     */
    private String electronicFlg;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}