package com.example.llmauthentication.mapper;

import com.example.llmauthentication.pojo.StuCourseData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author arthur
* @description 针对表【stu_course_data】的数据库操作Mapper
* @createDate 2024-05-21 09:21:59
* @Entity com.example.llmauthentication.pojo.StuCourseData
*/
public interface StuCourseDataMapper extends BaseMapper<StuCourseData> {

    void insertBatch(@Param("stuCourseDataList") List<StuCourseData> stuCourseDataList);
}




