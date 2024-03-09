package com.example.llmauthentication.mapper;

import com.example.llmauthentication.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE external_user_id = #{externalUserId}")
    User findByExternalUserId(@Param("externalUserId") String externalUserId);


    @Insert("INSERT INTO user (external_user_id, username, student_id, last_login_time, creation_time,can_access) " +
            "VALUES (#{externalUserId}, #{username}, #{studentId}, #{lastLoginTime}, #{creationTime},#{canAccess})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insertUser(User user);

    @Update("UPDATE user SET can_access = #{canAccess} WHERE student_id = #{studentId}")
    void updateCanAccess(@Param("studentId") String studentId, @Param("canAccess") int canAccess);
    @Delete("DELETE FROM user WHERE external_user_id = #{externalUserId}")
    void deleteByExternalUserId(@Param("externalUserId") String externalUserId);

}