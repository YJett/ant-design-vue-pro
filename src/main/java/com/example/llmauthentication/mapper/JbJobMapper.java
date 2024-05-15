package com.example.llmauthentication.mapper;

import com.example.llmauthentication.pojo.JbJob;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
* @author arthur
* @description 针对表【jb_job】的数据库操作Mapper
* @createDate 2024-05-07 08:55:44
* @Entity com.example.llmauthentication.pojo.JbJob
*/
public interface JbJobMapper extends BaseMapper<JbJob> {

    @Delete("delete from jb_job where jobId = #{jobid}")
    void deleteByJobName(@Param("jobid") Integer jobid);
}




