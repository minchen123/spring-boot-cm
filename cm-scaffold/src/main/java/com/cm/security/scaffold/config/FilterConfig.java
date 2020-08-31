package com.cm.security.scaffold.config;

import com.cm.security.scaffold.filter.CrosFilter;
import com.cm.security.scaffold.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description todo
 * @Author cm
 * @Date 2020/8/28 9:39
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registrationLoginBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("LoginFilter");
        filterRegistrationBean.setOrder(1);
        return  filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean registrationCrosBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CrosFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("CrosFilter");
        filterRegistrationBean.setOrder(1);
        return  filterRegistrationBean;
    }

}
