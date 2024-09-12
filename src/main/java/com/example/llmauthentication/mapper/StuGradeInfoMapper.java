package com.example.llmauthentication.mapper;

import com.example.llmauthentication.pojo.StuGradeInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author arthur
* @description 针对表【stu_grade_info】的数据库操作Mapper
* @createDate 2024-05-19 15:54:20
* @Entity com.example.llmauthentication.pojo.StuGradeInfo
*/
public interface StuGradeInfoMapper extends BaseMapper<StuGradeInfo> {

    //批量导入
    void insertBatch(@Param("stuGradeInfoList") List<StuGradeInfo> stuGradeInfoList);
}




