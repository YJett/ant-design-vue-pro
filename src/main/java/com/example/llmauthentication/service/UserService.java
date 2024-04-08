package com.example.llmauthentication.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.llmauthentication.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface UserService {
    User registerOrLoadUser(Map<String, Object> attributes);
    User findByExternalUserId(String externalUserId);
    void insertUser(User user);
    void updateCanAccess(String studentId, int canAccess);
    void deleteUser(String externalUserId);
    int  saveOrUpdateFromExcel(InputStream inputStream)  throws IOException;

    IPage<User>  selectUserPage(int currentPage, int pageSize, String externalUserId, String username,Integer canAccess);
}