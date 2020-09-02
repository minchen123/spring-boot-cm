package com.cm.security.scaffold.controller;

import com.cm.security.scaffold.vo.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 系统配置
 * @Author cm
 * @Date 2020/9/2 16:22
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "系统模块")
public class SystemController {

    @PostMapping("/save")
    @ApiOperation("保存")
    public Object saveInfo(SysUser sysUser) {
        return sysUser;
    }

}
