package com.example.llmauthentication.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName kp_knowledge_point
 */
@TableName(value ="kp_knowledge_point")
@Data
public class KpKnowledgePoint implements Serializable {
    private Integer schid;
    @ExcelProperty(value = "知识点编号",index = 0)
    private Integer knowledgeid;
    @ExcelProperty(value = "知识点名称",index = 1)
    private String knowledgenm;
    @ExcelProperty(value = "等级",index = 2)
    private Integer flag;
    @ExcelProperty(value = "上级知识点编号",index = 3)
    private Integer uplevel;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;
}