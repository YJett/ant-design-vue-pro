package com.example.llmauthentication.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.llmauthentication.pojo.CourseInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author arthur
* @description 针对表【course_info】的数据库操作Service
* @createDate 2024-05-16 10:27:17
*/
public interface CourseInfoService extends IService<CourseInfo> {

    void importCourseData(MultipartFile file, Integer schId) throws IOException;
}
