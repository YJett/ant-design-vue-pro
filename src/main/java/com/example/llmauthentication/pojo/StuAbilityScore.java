package com.example.llmauthentication.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName stu_ability_score
 */
@TableName(value ="stu_ability_score")
public class StuAbilityScore implements Serializable {
    /**
     * 标识号
     */
    @TableId(type = IdType.AUTO)
    private Integer itemid;

    /**
     * 岗位ID
     */
    private Integer jobid;

    /**
     * 学校ID
     */
    private Integer schid;

    /**
     * 学生ID
     */
    private Integer studentid;

    /**
     * 能力ID
     */
    private Integer abilityid;

    /**
     * 能力分值
     */
    private Double score;

    /**
     * 
     */
    private Date createtime;

    /**
     * 
     */
    private Date updatetime;

    /**
     * level
     */
    private Integer lv;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 标识号
     */
    public Integer getItemid() {
        return itemid;
    }

    /**
     * 标识号
     */
    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    /**
     * 岗位ID
     */
    public Integer getJobid() {
        return jobid;
    }

    /**
     * 岗位ID
     */
    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    /**
     * 学校ID
     */
    public Integer getSchid() {
        return schid;
    }

    /**
     * 学校ID
     */
    public void setSchid(Integer schid) {
        this.schid = schid;
    }

    /**
     * 学生ID
     */
    public Integer getStudentid() {
        return studentid;
    }

    /**
     * 学生ID
     */
    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    /**
     * 能力ID
     */
    public Integer getAbilityid() {
        return abilityid;
    }

    /**
     * 能力ID
     */
    public void setAbilityid(Integer abilityid) {
        this.abilityid = abilityid;
    }

    /**
     * 能力分值
     */
    public Double getScore() {
        return score;
    }

    /**
     * 能力分值
     */
    public void setScore(Double score) {
        this.score = score;
    }

    /**
     * 
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * level
     */
    public Integer getLv() {
        return lv;
    }

    /**
     * level
     */
    public void setLv(Integer lv) {
        this.lv = lv;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        StuAbilityScore other = (StuAbilityScore) that;
        return (this.getItemid() == null ? other.getItemid() == null : this.getItemid().equals(other.getItemid()))
            && (this.getJobid() == null ? other.getJobid() == null : this.getJobid().equals(other.getJobid()))
            && (this.getSchid() == null ? other.getSchid() == null : this.getSchid().equals(other.getSchid()))
            && (this.getStudentid() == null ? other.getStudentid() == null : this.getStudentid().equals(other.getStudentid()))
            && (this.getAbilityid() == null ? other.getAbilityid() == null : this.getAbilityid().equals(other.getAbilityid()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getLv() == null ? other.getLv() == null : this.getLv().equals(other.getLv()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getItemid() == null) ? 0 : getItemid().hashCode());
        result = prime * result + ((getJobid() == null) ? 0 : getJobid().hashCode());
        result = prime * result + ((getSchid() == null) ? 0 : getSchid().hashCode());
        result = prime * result + ((getStudentid() == null) ? 0 : getStudentid().hashCode());
        result = prime * result + ((getAbilityid() == null) ? 0 : getAbilityid().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getLv() == null) ? 0 : getLv().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemid=").append(itemid);
        sb.append(", jobid=").append(jobid);
        sb.append(", schid=").append(schid);
        sb.append(", studentid=").append(studentid);
        sb.append(", abilityid=").append(abilityid);
        sb.append(", score=").append(score);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", lv=").append(lv);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}