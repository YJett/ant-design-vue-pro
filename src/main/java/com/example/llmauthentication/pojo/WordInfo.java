package com.example.llmauthentication.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author ranyouwei
 * @Description：Word的所有信息
 * @date 2025/3/19
 */
@Data
public class WordInfo {
    /**
     * 学校名
     */
    private String schnm;

    /**
     * 学生姓名
     */
    private String studentnm;

    /**
     * 院系
     */
    private String department;

    /**
     * 专业
     */
    private String major;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 政治面貌（选项）
     */
    private String party;

    /**
     * 电子邮件
     */
    private String Email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 主修课程
     */
    private String majorCourse;


    /**
     * 技能证书
     */
    private String certinm;

    /**
     * 竞赛名称
     */
    private String contestnm;


}
