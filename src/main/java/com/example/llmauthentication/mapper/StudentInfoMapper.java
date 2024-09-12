package com.example.llmauthentication.mapper;

import com.example.llmauthentication.pojo.StudentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.llmauthentication.pojo.StudentQueryParams;
import com.example.llmauthentication.utils.StudentInfoProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
* @author arthur
* @description 针对表【student_info】的数据库操作Mapper
* @createDate 2024-05-19 15:17:34
* @Entity com.example.llmauthentication.pojo.StudentInfo
*/
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {

    @Select("CALL SF_INS_ABILITY_ALL()")
    void callSF_INS_ABILITY_ALL();

    @Select("CALL SF_INS_KNOWLEDGE_ALL()")
    void callSF_INS_KNOWLEDGE_ALL();

    @Select("{CALL SF_INS_CHARACTER(#{schId, mode=IN, jdbcType=INTEGER}, #{studentNo, mode=IN, jdbcType=VARCHAR})}")
    void callSFInsCharacter(@Param("schId") Integer schId, @Param("studentNo") String studentNo);

    @SelectProvider(type = StudentInfoProvider.class, method = "buildStudentInfoQuery")
    List<Map<String, Object>> getStudentInfo(@Param("params") StudentQueryParams params);

    //学生数据批量插入方法
    void insertBatch(@Param("studentInfoList") List<StudentInfo> studentInfoList);
}




