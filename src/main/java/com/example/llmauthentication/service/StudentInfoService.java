package com.example.llmauthentication.service;

import com.example.llmauthentication.pojo.StudentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.llmauthentication.pojo.StudentQueryParams;

import java.util.List;
import java.util.Map;

/**
* @author arthur
* @description 针对表【student_info】的数据库操作Service
* @createDate 2024-05-19 15:17:34
*/
public interface StudentInfoService extends IService<StudentInfo> {

    public List<Map<String, Object>> getStudentInfo(StudentQueryParams params);
}
