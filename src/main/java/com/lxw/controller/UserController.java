package com.lxw.controller;


import com.lxw.mapper.UserMapper;
import com.lxw.model.Msg;
import com.lxw.service.IUserService;
import com.lxw.util.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxwmmd
 * @since 2021-04-24
 */
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户登录", notes = "登录--不进行拦截")
//    @PostMapping("/login")
    @GetMapping("/login")
    public Msg login(@RequestParam("username") String username,
                     @RequestParam("password") String password) {
        String realPassword = userService.getPassword(username);
        if (realPassword == null) {
            return Msg.fail().add("info","用户名错误");
        } else if (!realPassword.equals(password)) {
            return Msg.fail().add("info","密码错误");
        } else {
            Msg msg = Msg.success().add("token", JWTUtil.createToken(username));

            if(username.equals("root")) {
                //管理员登陆成功 就加入菜单
                List<String> menu = userMapper.getMenu(username);
                msg.addMenu("carte", menu);
            }

            return msg;

        }
    }


    @ApiOperation(value = "无权限", notes = "无权限跳转的接口")
    @RequestMapping(path = "/unauthorized/{message}")
    public Msg unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return Msg.fail().add("info",message);
    }

    @ApiOperation(value = "特定用户访问", notes = "拥有 user, admin 角色的用户可以访问下面的页面")
    @PostMapping("/getMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    /** 如果有多个权限/角色验证的时候中间用“，”隔开，默认是所有列出的权限/角色必须同时满足才生效。
     * 但是在注解中有logical = Logical.OR这块。这里可以让权限控制更灵活些。
     *如果将这里设置成OR，表示所列出的条件只要满足其中一个就可以，如果不写或者设置成logical = Logical.AND，
     * 表示所有列出的都必须满足才能进入方法。
     */
    public Msg getMessage() {
        return Msg.success().add("info","成功获得信息！");
    }

    @ApiOperation(value = "Vip用户访问", notes = "拥有 vip 权限可以访问该页面")
    @PostMapping("/getVipMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequiresPermissions("vip")
    public Msg getVipMessage() {
        return Msg.success().add("info","成功获得 vip 信息！");
    }


}
