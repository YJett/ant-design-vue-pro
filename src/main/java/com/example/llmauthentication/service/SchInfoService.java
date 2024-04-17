package com.example.llmauthentication.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.SchInfo;
import com.example.llmauthentication.pojo.SchInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author arthur
* @description 针对表【company_info】的数据库操作Service
* @createDate 2024-04-08 17:06:47
*/
public interface SchInfoService extends IService<SchInfo> {
    Result login(String schName, String password);

    Page<SchInfo> getSchPage(int current, int size, String email, String schName);

    boolean deleteSch(Long id);

    boolean createSch(SchInfoVo schInfoVo);

    boolean updateSch(SchInfoVo schInfoVo);

    boolean deleteBatchSch(List<Long> ids);


    void importData(MultipartFile file);
}