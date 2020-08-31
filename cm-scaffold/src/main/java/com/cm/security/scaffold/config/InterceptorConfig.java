package com.cm.security.scaffold.config;

import com.cm.security.scaffold.Interceptor.LogsHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description todo
 * @Author cm
 * @Date 2020/8/28 14:03
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogsHandlerInterceptor());
    }

}
