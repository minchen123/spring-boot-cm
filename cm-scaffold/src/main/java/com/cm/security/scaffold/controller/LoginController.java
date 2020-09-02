package com.cm.security.scaffold.controller;

import com.cm.security.scaffold.vo.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 登录接口
 * @Author cm
 * @Date 2020/7/16 11:12
 */
@RequestMapping("/user")
@RestController
@Api(tags = "登录模块")
public class LoginController {

    @GetMapping("/login")
    @ApiOperation("登录接口调试")
    public Object login(){
        return "123";
    }

    @PostMapping("/regist")
    @ApiOperation("注册接口在线测试")
    public Object regist(SysUser sysUser) {
        return sysUser;
    }

}
