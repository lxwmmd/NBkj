package com.lxw.service;

import com.lxw.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxwmmd
 * @since 2021-04-24
 */
@Service
public interface IUserService extends IService<User> {



    public String getPassword(String username);

    public int checkUserBanStatus(String username);

    public String getRole(String username);

    public String getRolePermission(String username);

    public String getPermission(String username);

}
