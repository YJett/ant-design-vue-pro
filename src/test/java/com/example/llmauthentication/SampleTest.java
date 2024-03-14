package com.example.llmauthentication;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.example.llmauthentication.mapper.UserMapper;
import com.example.llmauthentication.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        User user = userMapper.findByExternalUserId("51265903080");
        System.out.println(user);

    }

}
