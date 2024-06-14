package com.example.llmauthentication.controller;

import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.StudentInfo;
import com.example.llmauthentication.pojo.StudentLiteracy;
import com.example.llmauthentication.pojo.StudentQueryParams;
import com.example.llmauthentication.service.StudentInfoService;
import com.example.llmauthentication.service.impl.StudentInfoImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private StudentInfoService studentInfoService;

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

    /**
     * 人岗匹配查询
     * @param params
     * @return
     */
    @PostMapping("api/studentInfo/query")
    public Result getStudentInfo(@RequestBody StudentQueryParams params) {
        List<Map<String, Object>> result = studentInfoService.getStudentInfo(params);
        return Result.success(result);
    }

    /**
     * 人岗匹配最新数据获取
     * @return
     */
    @PostMapping("api/studentInfo/getNewData")
    public Result getNewData() {
        studentInfoService.getNewData();
        return Result.success();
    }

    /**
     * 学生素养数据
     */
    @PostMapping("api/student/literacy")
    public Result getStudentLiteracy(@RequestParam("schId") Integer schId,@RequestParam("studentNo") String studentNo) {
        StudentLiteracy studentLiteracy = studentInfoService.getStudentLiteracy(schId,studentNo);
        return Result.success(studentLiteracy);
    }
}

