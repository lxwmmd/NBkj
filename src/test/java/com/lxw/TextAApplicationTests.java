package com.lxw;

import com.lxw.mapper.UserMapper;
import com.lxw.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TextAApplicationTests {

    @Autowired
    IUserService userService;
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
       System.out.println("userService.selectById(1) = " + userService.getPassword("萧何"));

    }
    @Test
    void getMenu(){
        System.out.println("getMenu = " + userMapper.getMenu("萧何"));
    }
}
