package com.example.llmauthentication.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * @TableName kp_knowledge_point
 */
@TableName(value ="kp_knowledge_point")
@Data
public class KpKnowledgePoint implements Serializable {
    @TableId
    private Integer schid;

    private Integer knowledgeid;

    private String knowledgenm;

    private Integer flag;

    private Integer uplevel;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

    private static final long serialVersionUID = 1L;
}