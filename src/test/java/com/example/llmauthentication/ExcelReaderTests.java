package com.example.llmauthentication;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.example.llmauthentication.utils.ExcelReader;
import com.example.llmauthentication.pojo.KpKnowledgePoint;

public class ExcelReaderTests {

    @Test
    public void testReadKnowledgePoints() throws IOException {
        // 假设本地有一个名为 "test.xlsx" 的Excel文件
        String filePath = "src/main/resources/excel-templates/knowledge.xlsx"; // 确保路径和文件名正确
        FileInputStream inputStream = new FileInputStream(filePath);

        // 创建一个模拟的MultipartFile
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",  // 参数名
                "test.xlsx",  // 文件名
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",  // MIME类型
                inputStream  // 输入流
        );

        // 调用要测试的方法
        List<KpKnowledgePoint> knowledgePoints = ExcelReader.readKnowledgePoints(multipartFile, 1); // 学校ID为1

        // 断言返回的知识点列表不为空
        assertNotNull(knowledgePoints, "知识点列表不应为空");
        assertFalse(knowledgePoints.isEmpty(), "知识点列表不应为空");

        // 检查列表中的第一个知识点
        KpKnowledgePoint firstPoint = knowledgePoints.get(0);
        assertEquals(1, firstPoint.getSchid(), "学校ID应为1");
        assertTrue(firstPoint.getKnowledgeid() > 0, "知识点ID应为正数");
        assertNotNull(firstPoint.getKnowledgenm(), "知识点名称不应为空");
    }
}
