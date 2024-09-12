package com.example.llmauthentication.mapper;

import com.example.llmauthentication.pojo.StuAttendanceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author arthur
* @description 针对表【stu_attendance_info】的数据库操作Mapper
* @createDate 2024-05-22 18:04:50
* @Entity com.example.llmauthentication.pojo.StuAttendanceInfo
*/
public interface StuAttendanceInfoMapper extends BaseMapper<StuAttendanceInfo> {

    void insertBatch(@Param("stuAttendanceInfoList") List<StuAttendanceInfo> stuAttendanceInfoList);
}




