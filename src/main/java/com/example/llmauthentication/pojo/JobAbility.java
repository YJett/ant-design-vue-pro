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
 * @TableName job_ability
 */
@TableName(value ="job_ability")
@Data
public class JobAbility implements Serializable {
    private Integer abilityid;

    private Integer abilityno;

    private String abilitynm;

    private Integer level;

    private Integer upabilityid;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

    private static final long serialVersionUID = 1L;
}