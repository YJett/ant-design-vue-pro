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
 * @TableName internship_info
 */
@TableName(value ="internship_info")
@Data
public class InternshipInfo implements Serializable {
    /**
     * 学校ID
     */
    private Integer schid;

    /**
     * 学号
     */
    private String studentno;

    /**
     * 实习企业行业类别码
     */
    private String industryCd;

    /**
     * 实习企业名称
     */
    private String internshipEnterprise;

    /**
     * 实习内容
     */
    private String content;

    /**
     * 实习时长（周）
     */
    private Integer duratio;

    /**
     * 实习成绩（百分制）
     */
    private Integer score;

    /**
     * 实习开始日期（YYYYMM）
     */
    private String startDate;

    /**
     * 实习结束日期（YYYYMM）
     */
    private String endDate;

    /**
     * 是否有违规行为
     */
    private String violationFlg;

    /**
     * 违规内容
     */
    private String violation;

    /**
     * 实习评价类别（从高到低A-F）
     */
    private String judgement;

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