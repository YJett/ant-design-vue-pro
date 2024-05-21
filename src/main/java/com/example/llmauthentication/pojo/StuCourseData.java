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
 * @TableName stu_course_data
 */
@TableName(value ="stu_course_data")
@Data
public class StuCourseData implements Serializable {
    /**
     * 学校ID
     */

    private Integer schid;

    /**
     * 学号
     */
    private String studentno;

    /**
     * 课程编号
     */
    private String courseno;

    /**
     * 课程名称
     */
    private String coursenm;

    /**
     * 课程开设学期
     */
    private String semester;

    /**
     * 平时成绩
     */
    private Double regularGrade;

    /**
     * 期末成绩
     */
    private Double endtermGrade;

    /**
     * 总评成绩
     */
    private Double score;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    /**
     * 学分
     */
    private Double credits;

    /**
     * 绩点
     */
    private Double gpa;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}