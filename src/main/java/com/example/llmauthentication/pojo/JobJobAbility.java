package com.example.llmauthentication.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @TableName job_job_ability
 */
@TableName(value ="job_job_ability")
@Data
public class JobJobAbility implements Serializable {
    private Integer itemid;

    private Integer jobid;

    private Integer abilityid;

    private Double abilitywt;

    private String comment;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

    private static final long serialVersionUID = 1L;
}