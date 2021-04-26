package com.lxw.service.impl;

import com.lxw.pojo.User;
import com.lxw.mapper.UserMapper;
import com.lxw.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxwmmd
 * @since 2021-04-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;

    public String getPassword(String username){
        return  userMapper.getPassword(username);
    }

    public int checkUserBanStatus(String username){
        return userMapper.checkUserBanStatus(username);
    }

    public String getRole(String username){
        return userMapper.getRole(username);
    }

    public String getRolePermission(String username){
        return userMapper.getRolePermission(username);
    }

    public String getPermission(String username){
        return userMapper.getPermission(username);
    }

}
