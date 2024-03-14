package com.example.llmauthentication.service;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.mapper.UserMapper;
import com.example.llmauthentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void saveOrUpdateFromExcel(InputStream inputStream) throws IOException {
        List<User> users = EasyExcel.read(inputStream).head(User.class).sheet().doReadSync();

        for (User user : users) {
            this.saveOrUpdate(user, Wrappers.<User>lambdaUpdate().eq(User::getExternalUserId, user.getExternalUserId()));
        }
    }



    @Override
    public User registerOrLoadUser(Map<String, Object> attributes) {
        String externalUserId = (String) attributes.get("userId");
        User user = userMapper.findByExternalUserId(externalUserId);

        if (user == null) {
            user = new User();
            user.setExternalUserId(externalUserId);
            user.setUsername((String) attributes.get("name"));
            user.setLastLoginTime(LocalDateTime.now()); // 或根据实际情况设置
            user.setCreationTime(LocalDateTime.now());
            user.setCanAccess(1);
            userMapper.insertUser(user);
        }

        return user;
    }
    public boolean saveOrUpdateUser(User user) {
        User existingUser = userMapper.getByExternalUserId(user.getExternalUserId());
        if (existingUser != null) {
            // Update the existing user
            user.setUserId(existingUser.getUserId());
            int updateResult = userMapper.updateByExternalUserId(user);
            return updateResult > 0;
        } else {
            // Save the new user
            return save(user);
        }
    }


    @Override
    public User findByExternalUserId(String externalUserId) {
        return userMapper.findByExternalUserId(externalUserId);
    }

    @Override
    @Transactional
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    @Transactional
    public void updateCanAccess(String external_user_id, int canAccess) {
        userMapper.updateCanAccess(external_user_id, canAccess);
    }

    @Override
    @Transactional
    public void deleteUser(String externalUserId) {
        // 这里假设你有一个通过externalUserId删除用户的Mapper方法
        userMapper.deleteByExternalUserId(externalUserId);
    }


}