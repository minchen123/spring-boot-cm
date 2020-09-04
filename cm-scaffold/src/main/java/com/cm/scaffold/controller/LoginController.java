package com.cm.scaffold.controller;

import com.cm.scaffold.base.BaseController;
import com.cm.scaffold.vo.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Api(tags = "登录功能")
@Slf4j
public class LoginController extends BaseController {

    @GetMapping("/login")
    @ApiOperation("登录接口调试")
    public Object login(){
        log.info("测试log4j2日志框架");
        return "123";
    }

    @PostMapping("/regist")
    @ApiOperation("注册接口在线测试")
    public Object regist(SysUser sysUser) {
        return sysUser;
    }

}
