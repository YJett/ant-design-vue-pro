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
 * @TableName stu_attendance_info
 */
@TableName(value ="stu_attendance_info")
@Data
public class StuAttendanceInfo implements Serializable {
    /**
     * 学校ID
     */
    private Integer schid;

    /**
     * 课程代码
     */
    private String courseno;

    /**
     * 课程名称（YYYY-YYYY学年第X学期）

     */
    private String coursenm;

    /**
     * 姓名
     */
    private String studentname;

    /**
     * 学号（YYYYMM）
     */
    private String studentno;

    /**
     * 学院
     */
    private String department;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级
     */
    private String studentclass;

    /**
     * 课堂积极性
     */
    private Double inEnthusiasm;

    /**
     * 课外积极性
     */
    private Double outEnthusiasm;

    /**
     * 考试成绩
     */
    private Double score;

    /**
     * 平时成绩
     */
    private Double regularGrade;

    /**
     * 积分
     */
    private Double points;

    /**
     * 出勤率
     */
    private Double attendanceRate;

    /**
     * 应出勤次数
     */
    private Integer attendance;

    /**
     * 实际出勤次数
     */
    private Integer really;

    /**
     * 缺勤次数
     */
    private Integer absent;

    /**
     * 迟到次数
     */
    private Integer late;

    /**
     * 早退次数
     */
    private Integer early;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    /**
     * 请假次数
     */
    private Integer leaverequests;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}