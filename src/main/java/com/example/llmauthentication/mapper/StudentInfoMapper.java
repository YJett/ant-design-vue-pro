package com.example.llmauthentication.mapper;

import com.example.llmauthentication.pojo.StudentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.llmauthentication.pojo.StudentQueryParams;
import com.example.llmauthentication.utils.StudentInfoProvider;
import org.apache.ibatis.annotations.Param;
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

    @SelectProvider(type = StudentInfoProvider.class, method = "buildStudentInfoQuery")
    List<Map<String, Object>> getStudentInfo(@Param("params") StudentQueryParams params);
}




