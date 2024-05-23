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
 * @TableName dormitory_health_info
 */
@TableName(value ="dormitory_health_info")
@Data
public class DormitoryHealthInfo implements Serializable {
    /**
     * 学校ID
     */
    private Integer schid;

    /**
     * 学号
     */
    private String studentno;

    /**
     * 宿舍号
     */
    private String roomNo;

    /**
     * 评价等级
     */
    private String level;

    /**
     * 检查时间（YYYYMMDD）
     */
    private Date checkDate;

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