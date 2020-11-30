package com.niull.mybatisplus;

import com.niull.mybatisplus.entity.User;
import com.niull.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class MybatisplusApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        User user = userMapper.selectById(2L);
        user.setAccount("18717648544");
        user.setEmail("lelement@qq.com");
        userMapper.updateById(user);
    }

}
