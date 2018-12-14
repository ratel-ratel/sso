package com.nice.filter;

import com.nice.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 * Created by nice on 2018/3/15.
 */
//@WebFilter(urlPatterns = "/*")
//@Order(1)//指定过滤器的执行顺序,值越大越靠后执行
public class MyFilterA implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 检查登录信息
     *
     * @param servletRequest  可以换成 HttpServletRequest
     * @param servletResponse 可以换成   HttpServletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUri = request.getRequestURI();
        if (!"/userA/userLogin".equals(requestUri)) {
            Cookie[] cookies = request.getCookies();
            boolean label = true;
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    String cookieName = cookie.getName();
                    String cookieValue = cookie.getValue();
                    if ("UserNoA".equals(cookieName) && StringUtil.isNotEmpty(cookieValue)) {
                        label = false;
                        break;
                    }
                }
            }
            if (label) {
                response.setStatus(403);
                response.getOutputStream().println("please login");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }


}
