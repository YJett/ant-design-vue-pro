package com.example.llmauthentication.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import com.example.llmauthentication.pojo.WordInfo;
import com.example.llmauthentication.service.StudentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ranyouwei
 * @Description：简历生成
 * @date 2025/8/2
 */
@Slf4j
@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private StudentInfoService studentInfoService;

    @PostMapping("/generateWord")
    public void generateWord(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("studentId") String studentId,
            @RequestParam("schId") Integer schId,
            HttpServletResponse response
    ) throws IOException {
        // 1. 从业务层获取你的 WordInfo
        WordInfo wordInfo = studentInfoService.getWordInfo(schId, studentId);

        // 2. 从上传的 MultipartFile 读取照片内容
        // 如果没有上传，则读取默认照片
        byte[] photoBytes;
        PictureType pictureType;

        if (photo != null && !photo.isEmpty()) {
            photoBytes = photo.getBytes();

            // 通过 content type 获取图片类型
            String contentType = photo.getContentType(); // 如 image/jpeg
            pictureType = resolvePictureType(contentType);
        } else {
            File defaultPhoto = new File("src/main/resources/resumePicture/empty.jpg");
            photoBytes = Files.readAllBytes(defaultPhoto.toPath());
            pictureType = PictureType.JPEG; // 默认图片类型
        }

        PictureRenderData picture = Pictures.ofBytes(photoBytes, pictureType)
                .size(91, 129)
                .create();

        // 4. 构造填充数据模型
        Map<String, Object> data = new HashMap<>();
        data.put("schnm", wordInfo.getSchnm());
        data.put("studentnm", wordInfo.getStudentnm());
        data.put("major", wordInfo.getMajor());
        data.put("birthday", wordInfo.getBirthday());
        data.put("department", wordInfo.getDepartment());
        data.put("hometown", wordInfo.getHometown());
        data.put("photo", picture);
        data.put("party", wordInfo.getParty());
        data.put("Email", wordInfo.getEmail());
        data.put("phone", wordInfo.getPhone());


        // --- 处理主修课程：手动拼接序号，每行三门，使用换行符 ---
        if (wordInfo.getCourseName() != null && !wordInfo.getCourseName().isEmpty()) {
            StringBuilder formattedCourses = new StringBuilder();
            int globalIndex = 1; // 全局序号
            for (int i = 0; i < wordInfo.getCourseName().size(); i++) {
                String course = wordInfo.getCourseName().get(i);
                formattedCourses.append(globalIndex).append(". ").append(course);

                // 每三门课程后或到达列表末尾时换行
                if ((globalIndex % 3 == 0) || (i == wordInfo.getCourseName().size() - 1)) {
                    if (i < wordInfo.getCourseName().size() - 1) { // 如果不是最后一项，就添加换行符
                        formattedCourses.append("\n");
                    }
                } else { // 否则添加两个空格间隔
                    formattedCourses.append("  ");
                }
                globalIndex++;
            }
            data.put("majorCourse", formattedCourses.toString()); // 使用新的字段名
        } else {
            data.put("majorCourse", "暂无主修课程"); // 提供一个默认值
        }


        // 处理竞赛，每行一个带序号
        if (wordInfo.getContestnm() != null && !wordInfo.getContestnm().isEmpty()) {
            // 将 List<String> 转换为 TextRenderData[] 数组
            TextRenderData[] contestRenderDataArray = wordInfo.getContestnm().stream()
                    .map(TextRenderData::new)
                    .toArray(TextRenderData[]::new);

            data.put("contestnm", new NumberingRenderData(NumberingFormat.DECIMAL, contestRenderDataArray));
        } else {
            data.put("contestnm", "");
        }

        // 处理技能证书，每行一个带序号
        if (wordInfo.getCertinm() != null && !wordInfo.getCertinm().isEmpty()) {
            // 将 List<String> 转换为 TextRenderData[] 数组
            TextRenderData[] certRenderDataArray = wordInfo.getCertinm().stream()
                    .map(TextRenderData::new)
                    .toArray(TextRenderData[]::new);

            data.put("certinm", new NumberingRenderData(NumberingFormat.DECIMAL, certRenderDataArray));
        } else {
            data.put("certinm", "");
        }

        // 5. 渲染并输出 Word
        try (XWPFTemplate template =
                     XWPFTemplate.compile("src/main/resources/templates/resumepoitl2.docx")) {
            template.render(data);

            // 设置响应类型
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

// 格式化日期
            String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String filename = wordInfo.getStudentnm()+ "简历" + "_" + dateStr + ".docx";
            String encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");

// 设置浏览器兼容的 Content-Disposition 头
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + filename + "\"; filename*=UTF-8''" + encodedFilename);



            template.write(response.getOutputStream());
        }
    }

    private PictureType resolvePictureType(String contentType) {
        if (contentType == null) return PictureType.JPEG; // 默认

        switch (contentType) {
            case "image/jpeg":
            case "image/jpg":
                return PictureType.JPEG;
            case "image/png":
                return PictureType.PNG;
            case "image/gif":
                return PictureType.GIF;
            case "image/bmp":
                return PictureType.BMP;
            default:
                return PictureType.JPEG; // 兜底
        }
    }
}
