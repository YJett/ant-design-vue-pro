package com.example.llmauthentication.service.impl;

import com.example.llmauthentication.mapper.JbJobMapper;
import com.example.llmauthentication.mapper.JobAbilityMapper;
import com.example.llmauthentication.mapper.JobJobAbilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbilityDataDeleteService {

    @Autowired
    private JbJobMapper jobMapper;

    @Autowired
    private JobJobAbilityMapper jobAbilityMapper;

    @Autowired
    private JobAbilityMapper abilityMapper;

    public void deleteData(Integer jobid) {
        // 1. 删除能力
        abilityMapper.deleteByJobName(jobid);

        // 2. 删除岗位能力关联
        jobAbilityMapper.deleteByJobName(jobid);

        // 3. 删除岗位
        jobMapper.deleteByJobName(jobid);
    }
}
