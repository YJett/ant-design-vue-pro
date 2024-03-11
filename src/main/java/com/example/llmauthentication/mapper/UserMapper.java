package com.example.llmauthentication.mapper;

import com.example.llmauthentication.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
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


    @Insert("INSERT INTO user (external_user_id, username, last_login_time, creation_time,can_access) " +
            "VALUES (#{externalUserId}, #{username}, #{lastLoginTime}, #{creationTime},#{canAccess})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insertUser(User user);

    @Update("UPDATE user SET can_access = #{canAccess} WHERE external_user_id = #{external_user_id}")
    void updateCanAccess(@Param("external_user_id") String external_user_id, @Param("canAccess") int canAccess);
    @Delete("DELETE FROM user WHERE external_user_id = #{externalUserId}")
    void deleteByExternalUserId(@Param("externalUserId") String externalUserId);

}