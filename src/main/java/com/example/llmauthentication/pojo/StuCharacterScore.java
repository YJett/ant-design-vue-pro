package com.example.llmauthentication.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName stu_character_score
 */
@TableName(value ="stu_character_score")
@Data
public class StuCharacterScore implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer schid;

    /**
     * 
     */
    private String stuno;

    /**
     * 
     */
    private Double outstanding;

    /**
     * 
     */
    private Double dedication;

    /**
     * 
     */
    private Double workstudy;

    /**
     * 
     */
    private Double participation;

    /**
     * 
     */
    private Double healty;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}