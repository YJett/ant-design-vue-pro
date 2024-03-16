package com.example.llmauthentication.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@TableName("`user`")
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;

    @ExcelProperty("学号/工号")
    private String externalUserId;

    private String username;

    private LocalDateTime lastLoginTime;
    private LocalDateTime creationTime;

    @ExcelProperty("可以访问")
    private int canAccess; // 新增字段

    public int getCanAccess() {
        return canAccess;
    }

    public void setCanAccess(int canAccess) {
        this.canAccess = canAccess;
    }

    public User() {
    }

    public Integer getUserId() {
        return this.userId;
    }

    public String getExternalUserId() {
        return this.externalUserId;
    }

    public String getUsername() {
        return this.username;
    }






    public LocalDateTime getLastLoginTime() {
        return this.lastLoginTime;
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public void setUsername(String username) {
        this.username = username;
    }




    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }




    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", externalUserId='" + externalUserId + '\'' +
                ", username='" + username + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", creationTime=" + creationTime +
                '}';
    }


// Getters and Setters
}
