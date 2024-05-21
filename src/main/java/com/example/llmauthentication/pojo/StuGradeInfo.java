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
 * @TableName stu_grade_info
 */
@TableName(value ="stu_grade_info")
@Data
public class StuGradeInfo implements Serializable {
    /**
     * 学校ID
     */

    private Integer schid;

    /**
     * 学号
     */
    @TableId
    private String studentno;

    /**
     * 公共基础课已获学分
     */
    private Integer generalCredit;

    /**
     * 专业基础课已获学分
     */
    private Integer subjectCredit;

    /**
     * 专业核心课已获学分
     */
    private Integer coreCredit;

    /**
     * 专业拓展课已获学分
     */
    private Integer developmentCredit;

    /**
     * 已获得总学分
     */
    private Integer total;

    /**
     * 公共基础课成绩
     */
    private Double generalScore;

    /**
     * 专业基础课成绩
     */
    private Double subjectScore;

    /**
     * 专业核心课成绩
     */
    private Double coreScore;

    /**
     * 专业拓展课成绩
     */
    private Double developmentScore;

    /**
     * 岗位实习成绩
     */
    private Double practiceScore;

    /**
     * 历年平均学分绩点
     */
    private Double gpa;

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