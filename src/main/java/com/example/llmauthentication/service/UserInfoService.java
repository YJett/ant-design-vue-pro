package com.example.llmauthentication.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.llmauthentication.pojo.UserInfoVo;

/**
* @author arthur
* @description 针对表【user_info】的数据库操作Service
* @createDate 2024-04-08 17:06:47
*/
public interface UserInfoService extends IService<UserInfo> {
    Result login(String username, String password);

    Page<UserInfo> getUserPage(int current, int size, String email, String userName);

    boolean deleteUser(Long id);

    boolean createUser(UserInfoVo userInfoVo);

    boolean updateUser(UserInfoVo userInfoVo);

}