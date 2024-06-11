package com.example.llmauthentication.controller;

import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.JbJob;
import com.example.llmauthentication.pojo.JobAbility;
import com.example.llmauthentication.service.JobAbilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * // 查询JobAbility
 *
 * @author ranyouwei
 * @date 2024/6/11
 */
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/jobAbility")
public class JobAbilityController {

    @Autowired
    private JobAbilityService jobAbilityService;

    @GetMapping("/getJobAbilityInfo")
    public Result<List<JobAbility>> getJobAbilityInfo() {
        List<JobAbility> jobInfoList = jobAbilityService.getJobAbilityInfo();
        return Result.success(jobInfoList);
    }
}
