package com.example.llmauthentication.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.pojo.JbJob;
import com.example.llmauthentication.service.JbJobService;
import com.example.llmauthentication.mapper.JbJobMapper;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author arthur
* @description 针对表【jb_job】的数据库操作Service实现
* @createDate 2024-05-07 08:55:44
*/
@Service
public class JbJobServiceImpl extends ServiceImpl<JbJobMapper, JbJob>
    implements JbJobService{
    @Resource
    private JbJobMapper JbJobMapper;
    @Override
    public List<JbJob> getAllJobInfo() {
        return JbJobMapper.selectList(null);
    }

}




