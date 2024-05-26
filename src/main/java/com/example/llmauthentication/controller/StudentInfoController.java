package com.example.llmauthentication.controller;

import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.service.impl.StudentInfoImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * // 学生信息导入
 *
 * @author ranyouwei
 * @date 2024/5/20
 */
@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class StudentInfoController {
    @Autowired
    private StudentInfoImportService studentInfoImportService;

    @PostMapping("api/import/studentInfoData")
    public Result importCourseData(@RequestParam("file") MultipartFile file, @RequestParam("schoolName") String schoolName) {
        try {
            studentInfoImportService.importAllStudentData(file, schoolName);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failed("IO异常");
        }
    }
}
