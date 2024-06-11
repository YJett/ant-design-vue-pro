package com.example.llmauthentication.service;

import com.example.llmauthentication.pojo.JbJob;
import com.example.llmauthentication.pojo.JobAbility;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author arthur
* @description 针对表【job_ability】的数据库操作Service
* @createDate 2024-05-07 09:03:30
*/
public interface JobAbilityService extends IService<JobAbility> {

    List<JobAbility> getJobAbilityInfo();
}
