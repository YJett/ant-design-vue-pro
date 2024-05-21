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
 * @TableName work_study_info
 */
@TableName(value ="work_study_info")
@Data
public class WorkStudyInfo implements Serializable {
    /**
     * 学校ID
     */
    private Integer schid;

    /**
     * 学号
     */
    private String studentno;

    /**
     * 勤工助学岗位名称
     */
    private String name;

    /**
     * 薪酬标准
     */
    private Double prizes;

    /**
     * 薪酬标准单位
     */
    private String unit;

    /**
     * 工作开始日期（YYYYMM）
     */
    private String startDate;

    /**
     * 工作结束日期（YYYYMM）
     */
    private String endDate;

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