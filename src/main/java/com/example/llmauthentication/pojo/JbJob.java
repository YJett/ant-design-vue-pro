package com.example.llmauthentication.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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

    private String jobdesp;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

    private String jobname;

    private static final long serialVersionUID = 1L;
}