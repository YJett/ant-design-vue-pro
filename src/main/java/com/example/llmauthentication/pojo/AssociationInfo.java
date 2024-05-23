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
 * @TableName association_info
 */
@TableName(value ="association_info")
@Data
public class AssociationInfo implements Serializable {
    /**
     * 学校ID
     */
    private Integer schid;

    /**
     * 学号
     */
    private String studentno;

    /**
     * 社团名称
     */
    private String associationNm;

    /**
     * 社团编号（校内自定）
     */
    private String associationNo;

    /**
     * 社团所属类别编号
     */
    private String associationCd;

    /**
     * 参加日期（YYYYMMDD）
     */
    private Date startDate;

    /**
     * 退出日期（YYYYMMDD）
     */
    private Date endDate;

    /**
     * 社团职务
     */
    private String duty;

    /**
     * 参与程度（重度，中度，轻度）
     */
    private String participationLevel;

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