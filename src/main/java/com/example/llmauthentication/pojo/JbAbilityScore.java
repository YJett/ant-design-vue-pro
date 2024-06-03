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
 * @TableName jb_ability_score
 */
@TableName(value ="jb_ability_score")
@Data
public class JbAbilityScore implements Serializable {
    /**
     * 标识号
     */
    @TableId(type = IdType.AUTO)
    private Integer itemid;

    /**
     * 学校ID
     */
    private Integer schid;

    /**
     * 学生ID
     */
    private Integer studentid;

    /**
     * 知识ID
     */
    private Integer knowledgeid;

    /**
     * 能力分值
     */
    private Double score;

    /**
     * 分类
     */
    private String type;

    /**
     * 
     */
    private Date createtime;

    /**
     * 
     */
    private Date updatetime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}