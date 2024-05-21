package com.example.llmauthentication.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
//import java.util.Date;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @TableName jb_job
 */
@TableName(value ="jb_job")
@Data
public class JbJob implements Serializable {
    @TableId(value = "jobId",type = IdType.AUTO)
    private Integer jobid;

    @TableField("jobDesp")
    private String jobdesp;

    @TableField("createTime")
    private LocalDateTime createtime;

    @TableField("updateTime")
    private LocalDateTime updatetime;

    @TableField("jobName")
    private String jobname;

    private static final long serialVersionUID = 1L;
}