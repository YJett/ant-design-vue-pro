package com.example.llmauthentication.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName jb_ability_knowledge
 */
@TableName(value ="jb_ability_knowledge")
@Data
public class JbAbilityKnowledge implements Serializable {
    private Integer schid;

    private Integer abilityid;

    private Integer knowledgeid;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;
}