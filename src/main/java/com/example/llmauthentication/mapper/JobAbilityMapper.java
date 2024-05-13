package com.example.llmauthentication.mapper;

import com.example.llmauthentication.pojo.JobAbility;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
* @author arthur
* @description 针对表【job_ability】的数据库操作Mapper
* @createDate 2024-05-07 09:03:30
* @Entity com.example.llmauthentication.pojo.JobAbility
*/
public interface JobAbilityMapper extends BaseMapper<JobAbility> {

    @Delete("delete from jb_ability where abilityid in (select abilityid from job_job_ability where jobid = (select jobid from jb_job where jobname = #{jobName}))")
    void deleteByJobName(@Param("jobName") String jobName);
}




