package com.cm.security.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @Description todo
 * @Author cm
 * @Date 2020/8/19 17:27
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // 配置请求地址的权限
            .authorizeRequests()
            .antMatchers("/test/demo").permitAll() // 所有用户可访问
            .antMatchers("/test/admin").hasRole("ADMIN") // 需要 ADMIN 角色
            .antMatchers("/test/normal").access("hasRole('ROLE_NORMAL')") // 需要 NORMAL 角色。
            // 任何请求，访问的用户都需要经过认证
            .anyRequest().authenticated()
            .and()
            // 设置 Form 表单登陆
            .formLogin()
//                    .loginPage("/login") // 登陆 URL 地址
            .permitAll() // 所有用户可访问
            .and()
            // 配置退出相关
            .logout()
//                    .logoutUrl("/logout") // 退出 URL 地址
            .permitAll(); // 所有用户可访问
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
            //默认内存
            inMemoryAuthentication()
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
            .withUser("admin").password("admin").roles("ADMIN")
             // 配置 normal 用户
            .and().withUser("normal").password("normal").roles("NORMAL");

    }

}
