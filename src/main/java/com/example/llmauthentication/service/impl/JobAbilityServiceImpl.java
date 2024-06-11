package com.example.llmauthentication.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.pojo.JbJob;
import com.example.llmauthentication.pojo.JobAbility;
import com.example.llmauthentication.service.JobAbilityService;
import com.example.llmauthentication.mapper.JobAbilityMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author arthur
* @description 针对表【job_ability】的数据库操作Service实现
* @createDate 2024-05-07 09:03:30
*/
@Service
public class JobAbilityServiceImpl extends ServiceImpl<JobAbilityMapper, JobAbility>
    implements JobAbilityService{

    @Autowired
    private JobAbilityMapper jobAbilityMapper;

    @Override
    public List<JobAbility> getJobAbilityInfo() {
        List<JobAbility> jobAbilities = jobAbilityMapper.selectList(null);
        return jobAbilities;
    }
}




