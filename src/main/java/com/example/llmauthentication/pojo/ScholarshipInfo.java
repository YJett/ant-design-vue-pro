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
 * @TableName scholarship_info
 */
@TableName(value ="scholarship_info")
@Data
public class ScholarshipInfo implements Serializable {
    /**
     * 学校ID
     */
    private Integer schid;

    /**
     * 学号
     */
    private String studentno;

    /**
     * 奖学金名称
     */
    private String name;

    /**
     * 奖学金类别
     */
    private String scholarshipclass;

    /**
     * 奖学金等级
     */
    private String level;

    /**
     * 奖学金金额（元）
     */
    private Double prizes;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    /**
     * 获得年份
     */
    private String yyyymm;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}