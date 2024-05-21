package com.example.llmauthentication.service;

import com.example.llmauthentication.pojo.JbJob;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author arthur
* @description 针对表【jb_job】的数据库操作Service
* @createDate 2024-05-07 08:55:44
*/
public interface JbJobService extends IService<JbJob> {
    List<JbJob> getAllJobInfo();

}
