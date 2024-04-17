package com.example.llmauthentication.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.ComInfo;
import com.example.llmauthentication.pojo.ComInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author arthur
* @description 针对表【company_info】的数据库操作Service
* @createDate 2024-04-08 17:06:47
*/
public interface ComInfoService extends IService<ComInfo> {


    Result login(String comName, String password);

    Page<ComInfo> getComPage(int current, int size, String email, String comName);

    boolean deleteCom(Long id);

    boolean createCom(ComInfoVo comInfoVo);

    boolean updateCom(ComInfoVo comInfoVo);
    boolean deleteBatchCom(List<Long> ids);

    boolean successBatchCom(List<Long> ids);

    void importData(MultipartFile file);
}