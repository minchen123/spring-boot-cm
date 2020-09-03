package com.cm.scaffold.config;

import com.cm.scaffold.Interceptor.LogsHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description 拦截配置
 * @Author cm
 * @Date 2020/8/28 14:03
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogsHandlerInterceptor())
            .addPathPatterns("/**");  //所有路径拦截
            //.excludePathPatterns("/login");
    }

}
