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
 * @TableName course_info
 */
@TableName(value ="course_info")
@Data
public class CourseInfo implements Serializable {
    /**
     * 课程ID
     */
    @TableId(type = IdType.AUTO)
    private Integer courseid;

    /**
     * 学校ID
     */
    @TableId
    private Integer schid;

    /**
     * 课程编号
     */
    private String courseno;

    /**
     * 课程名称
     */
    private String coursenm;

    /**
     * 学时数
     */
    private Integer classhour;

    /**
     * 学分
     */
    private Integer credit;

    /**
     * 主讲教师（如有多个，英文半角逗号分隔）
     */
    private String teacher;

    /**
     * 主要单元数
     */
    private Integer units;

    /**
     * 课程性质标记1：理论课，2：实践课，3：综合课
     */
    private String flg;

    /**
     * 知识点编号(对应一级)
     */
    private Integer knowledgeid;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    /**
     * 课程类型（公共基础课，专业基础课，专业核心课，专业拓展课）
     */
    private String type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}