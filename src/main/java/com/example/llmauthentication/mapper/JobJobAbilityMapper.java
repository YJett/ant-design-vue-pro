package com.example.llmauthentication.mapper;

import com.example.llmauthentication.pojo.JobJobAbility;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
* @author arthur
* @description 针对表【job_job_ability】的数据库操作Mapper
* @createDate 2024-05-07 09:03:02
* @Entity com.example.llmauthentication.pojo.JobJobAbility
*/
public interface JobJobAbilityMapper extends BaseMapper<JobJobAbility> {

    @Delete("delete from job_job_ability where jobid = (select jobid from jb_job where jobname = #{jobName})")
    void deleteByJobName(@Param("jobName") String jobName);
}




