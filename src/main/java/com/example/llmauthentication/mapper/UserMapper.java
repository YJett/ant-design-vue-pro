package com.example.llmauthentication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.llmauthentication.model.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE external_user_id = #{externalUserId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "externalUserId", column = "external_user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "lastLoginTime", column = "last_login_time"),
            @Result(property = "creationTime", column = "creation_time"),
            @Result(property = "canAccess", column = "can_access")
    })
    User findByExternalUserId(@Param("externalUserId") String externalUserId);

    @Select("SELECT * FROM `user` WHERE `external_user_id` = #{externalUserId}")

    User getByExternalUserId(String externalUserId);
    @Update("UPDATE `user` " +
            "<set>" +
            "  <if test='username != null'>username = #{username},</if>" +
            "  <if test='lastLoginTime != null'>last_login_time = #{lastLoginTime},</if>" +
            "  <if test='creationTime != null'>creation_time = #{creationTime},</if>" +
            "  <if test='canAccess != null'>can_access = #{canAccess},</if>" +
            "</set>" +
            "WHERE `external_user_id` = #{externalUserId}")
    int updateByExternalUserId(User user);

    @Insert("INSERT INTO user (external_user_id, username, last_login_time, creation_time,can_access) " +
            "VALUES (#{externalUserId}, #{username}, #{lastLoginTime}, #{creationTime},#{canAccess})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insertUser(User user);

    @Update("UPDATE user SET can_access = #{canAccess} WHERE external_user_id = #{external_user_id}")
    void updateCanAccess(@Param("external_user_id") String external_user_id, @Param("canAccess") int canAccess);
    @Delete("DELETE FROM user WHERE external_user_id = #{externalUserId}")
    void deleteByExternalUserId(@Param("externalUserId") String externalUserId);

}