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
 * @TableName unit_info
 */
@TableName(value ="unit_info")
@Data
public class UnitInfo implements Serializable {
    /**
     * 学校ID
     */
    @TableId
    private Integer schid;

    /**
     * 课程编号
     */
    @TableId
    private Integer courseid;

    /**
     * 单元号
     */
    private Integer unitid;

    /**
     * 单元名称
     */
    private String unitnm;

    /**
     * 学时数
     */
    private Integer classhour;

    /**
     * 知识点编号：关联二级知识点编号
     */
    private Integer knowledgeid;

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