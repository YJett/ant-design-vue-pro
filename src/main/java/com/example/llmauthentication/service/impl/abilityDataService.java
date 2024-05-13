package com.example.llmauthentication.service.impl;

import com.example.llmauthentication.mapper.JbJobMapper;
import com.example.llmauthentication.mapper.JobAbilityMapper;
import com.example.llmauthentication.mapper.JobJobAbilityMapper;
import com.example.llmauthentication.pojo.JbJob;
import com.example.llmauthentication.pojo.JobAbility;
import com.example.llmauthentication.pojo.JobJobAbility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class abilityDataService {
    @Autowired
    private JbJobMapper jobMapper;

    @Autowired
    private JobJobAbilityMapper jobAbilityMapper;

    @Autowired
    private JobAbilityMapper abilityMapper;

    @Autowired
    private AbilityDataDeleteService abilityDataDeleteService;

    @Transactional
    public void importData(MultipartFile file) throws IOException {
        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            // 第一步：导入岗位数据
            Row firstRow = sheet.getRow(0);
            Row secondRow = sheet.getRow(1);

            //导入前先删除原有数据
            String jobName = firstRow.getCell(1).getStringCellValue();
            abilityDataDeleteService.deleteData(jobName);


            JbJob job = new JbJob();
            job.setJobname(firstRow.getCell(1).getStringCellValue()); // 岗位名称

            // 如果 `JobDescription` 可能为空，使用空值检查
            if (secondRow != null && secondRow.getCell(1) != null && secondRow.getCell(1).getCellType() == CellType.STRING) {
                job.setJobdesp(secondRow.getCell(1).getStringCellValue()); // 岗位描述
            } else {
                job.setJobdesp(""); // 设置默认值，避免空指针异常
            }

            job.setCreatetime(LocalDateTime.now());
            job.setUpdatetime(LocalDateTime.now());

            jobMapper.insert(job); // 插入岗位
            Integer jobId = job.getJobid(); // 获取岗位 ID

            // 第二步：处理能力数据
            List<JobAbility> abilities = new ArrayList<>();
            for (int i = 3; i <= sheet.getLastRowNum(); i++) { // 从第四行开始
                Row row = sheet.getRow(i);

                if (row != null) {
                    //可能报错
                    String abilityNo =  row.getCell(0).getStringCellValue(); // 能力编号
                    String abilityNm = row.getCell(1).getStringCellValue(); // 能力名称
                    Integer level = (int) row.getCell(2).getNumericCellValue(); // 能力层级
                    Integer upabilityId = null; // 上层能力编号

                    if (row.getCell(3) != null) {
                        upabilityId = (int) row.getCell(3).getNumericCellValue();
                    }

                    JobAbility ability = new JobAbility();
                    ability.setAbilityno(abilityNo);
                    ability.setAbilitynm(abilityNm);
                    ability.setLevel(level);
                    ability.setUpabilityid(upabilityId);
                    ability.setCreatetime(LocalDateTime.now());
                    ability.setUpdatetime(LocalDateTime.now());

                    abilityMapper.insert(ability); // 插入能力
                    Integer abilityId = ability.getAbilityid(); // 获取能力 ID

                    if (upabilityId == null) { // 如果上级能力编号为空
                        JobJobAbility jobAbility = new JobJobAbility();
                        jobAbility.setJobid(jobId);
                        jobAbility.setAbilityid(abilityId);
                        jobAbility.setCreatetime(LocalDateTime.now());
                        jobAbility.setUpdatetime(LocalDateTime.now());

                        jobAbilityMapper.insert(jobAbility); // 插入岗位与能力的关系
                    }
                }
            }
        }
    }
}
