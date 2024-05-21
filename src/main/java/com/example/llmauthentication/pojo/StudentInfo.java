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
 * @TableName student_info
 */
@TableName(value ="student_info")
@Data
public class StudentInfo implements Serializable {
    /**
     * 学生ID
     */
    @TableId(type = IdType.AUTO)
    private Integer studentid;

    /**
     * 学校ID
     */

    private Integer schid;

    /**
     * 学生姓名
     */
    private String studentnm;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 专业
     */
    private String major;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    /**
     * 学校名
     */
    private String schnm;

    /**
     * 院系
     */
    private String department;

    /**
     * 班级
     */

    private String studentclass;

    /**
     * 学号
     */
    private String studentno;

    /**
     * 性别
     */
    private String gender;

    /**
     * 入校时间
     */
    private String enrollmentDate;

    /**
     * 是否党员（选项）
     */
    private String party;

    /**
     * 生源地
     */
    private String hometown;

    /**
     * 健康状况（选项）
     */
    private String health;

    /**
     * 有无残疾（选项）
     */
    private String disability;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}