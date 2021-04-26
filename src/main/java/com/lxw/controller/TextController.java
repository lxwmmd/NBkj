package com.lxw.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextController {

    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequestMapping("/hello")
    public String hi()
    {
        return  "hello !";
    }
}
