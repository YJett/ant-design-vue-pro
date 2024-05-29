package com.example.llmauthentication.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.llmauthentication.dto.AbilityScore;
import com.example.llmauthentication.pojo.StuAbilityScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ECNU
* @description 针对表【stu_ability_score】的数据库操作Mapper
* @createDate 2024-05-29 14:14:46
* @Entity .com.example.llmauthentication.service.JobJobAbilityService.StuAbilityScore
*/
public interface StuAbilityScoreMapper extends BaseMapper<StuAbilityScore> {
    void callSF_INS_ABILITY(@Param("IN_JOBID") Integer IN_JOBID, @Param("IN_SCHID") Integer IN_SCHID, @Param("IN_STUNO") String IN_STUNO);

        List<AbilityScore> getAbilityScores(@Param("jobId") Integer jobId, @Param("schId") Integer schId, @Param("studentId") String studentId, @Param("lv") Integer lv, @Param("upabilityId") String upabilityId);

}




