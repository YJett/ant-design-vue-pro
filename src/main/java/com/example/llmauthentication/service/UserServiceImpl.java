package com.example.llmauthentication.service;

import com.example.llmauthentication.mapper.UserMapper;
import com.example.llmauthentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User registerOrLoadUser(Map<String, Object> attributes) {
        String externalUserId = (String) attributes.get("external_user_id");
        User user = userMapper.findByExternalUserId(externalUserId);

        if (user == null) {
            user = new User();
            user.setExternalUserId(externalUserId);
            user.setUsername((String) attributes.get("username"));
            user.setLastLoginTime(LocalDateTime.now()); // 或根据实际情况设置
            user.setCreationTime(LocalDateTime.now());
            user.setCanAccess(1);
            userMapper.insertUser(user);
        }

        return user;
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
    public void updateCanAccess(String studentId, int canAccess) {
        userMapper.updateCanAccess(studentId, canAccess);
    }

    @Override
    @Transactional
    public void deleteUser(String externalUserId) {
        // 这里假设你有一个通过externalUserId删除用户的Mapper方法
        userMapper.deleteByExternalUserId(externalUserId);
    }
}