package com.example.llmauthentication.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.mapper.ComInfoMapper;
import com.example.llmauthentication.pojo.ComInfo;
import com.example.llmauthentication.pojo.ComInfoVo;
import com.example.llmauthentication.service.ComInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author arthur
* @description 针对表【company_info】的数据库操作Service实现
* @createDate 2024-04-08 17:06:47
*/
@Service
public class ComInfoServiceImpl extends ServiceImpl<ComInfoMapper, ComInfo>
    implements ComInfoService {
    @Resource
    private ComInfoMapper comInfoMapper;

    @Override
    public boolean deleteCom(Long id) {
       ComInfo com = this.getById(id);
        if (com == null) {
            return false;
        } else {
            com.setStatus("9");
            return this.updateById(com);
        }
    }

    @Override
    public boolean createCom(ComInfoVo comInfoVo) {

        ComInfo comInfo = new ComInfo();
        BeanUtils.copyProperties(comInfoVo, comInfo);

        int result = comInfoMapper.insert(comInfo);

        return result > 0;    }

    @Override
    public boolean updateCom(ComInfoVo comInfoVo) {
        ComInfo comInfo = new ComInfo();
        BeanUtils.copyProperties(comInfoVo, comInfo);

        int result = comInfoMapper.updateById(comInfo);

        // 如果更新成功，result 等于操作影响的行数，一般为1，
        // 如果更新失败（例如找不到相应id的数据行），那么result 等于0
        return result > 0;
    }


    @Override
    public Result login(String comName, String password) {
        QueryWrapper<ComInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comName",comName);
        queryWrapper.eq("pwd", password);
        ComInfo com = this.baseMapper.selectOne(queryWrapper);
        if (com == null) {
            return Result.failed("用户不存在");
        }
        ComInfoVo comInfoVo = new ComInfoVo();
        BeanUtils.copyProperties(com, comInfoVo);
        comInfoVo.setToken("123456");
        Integer comId = comInfoVo.getComId();
        StpUtil.login(comId);
        return Result.success(comInfoVo);
    }

    @Override
    public Page<ComInfo> getComPage(int current, int size, String email, String comName) {
        Page<ComInfo> page = new Page<>(current, size);

        QueryWrapper<ComInfo> queryWrapper = new QueryWrapper<>();

        if (email != null && !email.isEmpty()) {
            queryWrapper = queryWrapper.like("email", email);
        }
        if (comName != null && !comName.isEmpty()) {
            queryWrapper = queryWrapper.like("comName", comName);
        }

        comInfoMapper.selectPage(page, queryWrapper);

        return page;
    }


}




