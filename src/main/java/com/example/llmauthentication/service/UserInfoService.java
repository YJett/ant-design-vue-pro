package com.example.llmauthentication.service;

import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author arthur
* @description 针对表【user_info】的数据库操作Service
* @createDate 2024-04-08 17:06:47
*/
public interface UserInfoService extends IService<UserInfo> {
    Result login(String username, String password);

    Result queryAllUsers(int pagenum, int limit);

}
