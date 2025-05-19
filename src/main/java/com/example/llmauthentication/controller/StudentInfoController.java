package com.example.llmauthentication.controller;

import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.StudentInfo;
import com.example.llmauthentication.pojo.StudentLiteracy;
import com.example.llmauthentication.pojo.StudentQueryParams;
import com.example.llmauthentication.pojo.WordInfo;
import com.example.llmauthentication.service.StudentInfoService;
import com.example.llmauthentication.service.impl.StudentInfoImportService;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    /**
     * 导入所有学生信息
     * @param file
     * @param schoolName
     * @return
     */
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

    /**
     * 基于模板生成Word简历
     *
     * @param shcId
     * @param studentNo
     */
    @GetMapping("api/generate_word")
    public void generateWord(@RequestParam("shcId") Integer shcId, @RequestParam("studentNo") String studentNo, HttpServletResponse response){

        WordInfo wordInfo = studentInfoService.getWordInfo(shcId, studentNo);

        try {
            // 设置 HTTP 响应的内容类型为 Microsoft Word 文档
            response.setContentType("application/msword");
            // 设置响应字符编码为 UTF-8
            response.setCharacterEncoding("utf-8");

            // 获取当前日期时间并格式化
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String timestamp = now.format(formatter);

            // 使用 URLEncoder 对文件名进行编码，以防止中文文件名在不同浏览器和操作系统下出现乱码问题
            String filename = URLEncoder.encode(wordInfo.getStudentnm() + "_" + timestamp, "utf-8");
            // 设置响应头，指定文档将以附件的形式下载，并定义文件名
            response.setHeader("Content-disposition", "attachment;filename=" + filename + ".doc");
            // 创建 Freemarker 的 Configuration 对象，设置默认的不兼容改进选项
            Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            configuration.setDefaultEncoding("utf-8");
            // 设置模板加载器，加载模板文件
            configuration.setTemplateLoader(new ClassTemplateLoader(getClass(), "/templates"));
            Template t = configuration.getTemplate("resume4.xml", "utf-8");
            // 创建 Writer 对象，用于将生成的文档写到输出流中，缓存区大小设为 10KB
            Writer out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8), 10240);
            // 将模型数据与模板结合生成 Word 文档，写入到 Writer 对象中
            t.process(wordInfo, out);
            out.close();
        } catch (Exception e) {
            log.info("生成Word文档异常，异常原因：{}", e.getMessage(), e);
            throw new RuntimeException("生成Word文档异常，异常原因：" + e.getMessage());
        }
    }


}

