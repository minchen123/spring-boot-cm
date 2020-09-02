package com.cm.security.scaffold.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description 拦截器作用能拿到请求和处理方法，但是拿不到方法的参数信息
 * @Author cm
 * @Date 2020/8/28 13:52
 */
public class LogsHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String uri = httpServletRequest.getRequestURI();
//        if(uri.indexOf("user") != -1) {
//            return true;
//        } else {
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println(request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
