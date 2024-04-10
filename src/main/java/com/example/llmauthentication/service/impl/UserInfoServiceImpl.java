package com.example.llmauthentication.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.UserInfo;
import com.example.llmauthentication.pojo.UserInfoVo;
import com.example.llmauthentication.service.UserInfoService;
import com.example.llmauthentication.mapper.UserInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author arthur
* @description 针对表【user_info】的数据库操作Service实现
* @createDate 2024-04-08 17:06:47
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean deleteUser(Long id) {
        UserInfo user = this.getById(id);
        if (user == null) {
            return false;
        } else {
            user.setStatus("9");
            return this.updateById(user);
        }
    }

    @Override
    public boolean createUser(UserInfoVo userInfoVo) {


        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoVo, userInfo);

        int result = userInfoMapper.insert(userInfo);


        return result > 0;    }

    @Override
    public boolean updateUser(UserInfoVo userInfoVo) {
        // 将 userInfoVo 转换为 UserInfo
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoVo, userInfo);

        int result = userInfoMapper.updateById(userInfo);

        // 如果更新成功，result 等于操作影响的行数，一般为1，
        // 如果更新失败（例如找不到相应id的数据行），那么result 等于0
        return result > 0;
    }


    @Override
    public Result login(String username, String password) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        queryWrapper.eq("pwd", password);
        UserInfo user = this.baseMapper.selectOne(queryWrapper);
        if (user == null) {
            return Result.failed("用户不存在");
        }
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(user, userInfoVo);
        userInfoVo.setToken("123456");
        Integer userId = userInfoVo.getUserId();
        StpUtil.login(userId);
        return Result.success(userInfoVo);
    }

    @Override
    public Page<UserInfo> getUserPage(int current, int size, String email, String userName) {
        Page<UserInfo> page = new Page<>(current, size);

        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();

        if (email != null && !email.isEmpty()) {
            queryWrapper = queryWrapper.like("email", email);
        }
        if (userName != null && !userName.isEmpty()) {
            queryWrapper = queryWrapper.like("userName", userName);
        }

        userInfoMapper.selectPage(page, queryWrapper);

        return page;
    }


}




