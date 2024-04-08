package com.example.llmauthentication.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
//        StpUtil.login(10001);
        return Result.success(userInfoVo);
    }
}




