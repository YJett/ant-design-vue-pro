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
 * @TableName green_channels_apply
 */
@TableName(value ="green_channels_apply")
@Data
public class GreenChannelsApply implements Serializable {
    /**
     * 学校ID
     */
    private Integer schid;

    /**
     * 学号
     */
    private String studentno;

    /**
     * 缓交金额
     */
    private Double amt;

    /**
     * 缓交类型
     */
    private String delayclass;

    /**
     * 申请年月（YYYYMM）
     */
    private String apply;

    /**
     * 家庭主要收入来源类型
     */
    private String incomeType;

    /**
     * 家庭人均月收入（人民币元）
     */
    private Double income;

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