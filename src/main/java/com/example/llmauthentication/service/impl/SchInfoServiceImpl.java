package com.example.llmauthentication.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.mapper.SchInfoMapper;
import com.example.llmauthentication.pojo.SchInfo;
import com.example.llmauthentication.pojo.SchInfoVo;
import com.example.llmauthentication.service.SchInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author arthur
* @description 针对表【sch_info】的数据库操作Service实现
* @createDate 2024-04-08 17:06:47
*/
@Service
public class SchInfoServiceImpl extends ServiceImpl<SchInfoMapper, SchInfo>
    implements SchInfoService {
    @Resource
    private SchInfoMapper schInfoMapper;

    @Override
    public boolean deleteSch(Long id) {
       SchInfo sch = this.getById(id);
        if (sch == null) {
            return false;
        } else {
            sch.setStatus("9");
            return this.updateById(sch);
        }
    }

    @Override
    public boolean createSch(SchInfoVo schInfoVo) {

        SchInfo schInfo = new SchInfo();
        BeanUtils.copyProperties(schInfoVo, schInfo);

        int result = schInfoMapper.insert(schInfo);

        return result > 0;    }

    @Override
    public boolean updateSch(SchInfoVo schInfoVo) {
        SchInfo schInfo = new SchInfo();
        BeanUtils.copyProperties(schInfoVo, schInfo);

        int result = schInfoMapper.updateById(schInfo);

        // 如果更新成功，result 等于操作影响的行数，一般为1，
        // 如果更新失败（例如找不到相应id的数据行），那么result 等于0
        return result > 0;
    }


    @Override
    public Result login(String schName, String password) {
        QueryWrapper<SchInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("schName",schName);
        queryWrapper.eq("pwd", password);
        SchInfo sch = this.baseMapper.selectOne(queryWrapper);
        if (sch == null) {
            return Result.failed("用户不存在");
        }
        SchInfoVo schInfoVo = new SchInfoVo();
        BeanUtils.copyProperties(sch, schInfoVo);
        schInfoVo.setToken("123456");
        Integer schId = schInfoVo.getSchId();
        StpUtil.login(schId);
        return Result.success(schInfoVo);
    }

    @Override
    public Page<SchInfo> getSchPage(int current, int size, String email, String schName) {
        Page<SchInfo> page = new Page<>(current, size);

        QueryWrapper<SchInfo> queryWrapper = new QueryWrapper<>();

        if (email != null && !email.isEmpty()) {
            queryWrapper = queryWrapper.like("email", email);
        }
        if (schName != null && !schName.isEmpty()) {
            queryWrapper = queryWrapper.like("schName", schName);
        }

        schInfoMapper.selectPage(page, queryWrapper);

        return page;
    }


}




