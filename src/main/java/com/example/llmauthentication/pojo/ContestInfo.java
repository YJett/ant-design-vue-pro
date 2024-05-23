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
 * @TableName contest_info
 */
@TableName(value ="contest_info")
@Data
public class ContestInfo implements Serializable {
    /**
     * 学校ID
     */
    private Integer schid;

    /**
     * 学号
     */
    private String studentno;

    /**
     * 竞赛等级（国家级，省级，行业或部门级，企业级，其他）
     */
    private String level;

    /**
     * 所获奖项
     */
    private String contestiid;

    /**
     * 竞赛名称
     */
    private String contestnm;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    private String contesttime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}