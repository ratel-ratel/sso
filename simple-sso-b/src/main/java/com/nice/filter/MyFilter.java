package com.nice.filter;

import com.nice.domain.UserB;
import com.nice.domain.UserRelevance;
import com.nice.service.UserBService;
import com.nice.service.UserRelevanceService;
import com.nice.service.impl.UserBServiceImpl;
import com.nice.service.impl.UserRelevanceServiceImpl;
import com.nice.utils.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器
 * Created by nice on 2018/3/15.
 */
//@WebFilter(urlPatterns = "/*")
//@Order(1)//指定过滤器的执行顺序,值越大越靠后执行
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        ServletContext sc = filterConfig.getServletContext();
//        XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
//
//        if (cxt != null && cxt.getBean("userBService") != null && userBService == null) {
//            userBService = (UserBService) cxt.getBean("userBService");
//        }
//        if (cxt != null && cxt.getBean("userRelevanceService") != null && userRelevanceService == null) {
//            userRelevanceService = (UserRelevanceService) cxt.getBean("userRelevanceService");
//        }
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

        String contextPath = request.getContextPath();
        String requestUri = request.getRequestURI();
        List<String> list=new ArrayList<String>();
        list.add("/userB/userLogin");
        list.add("/userRelevance/queryList");
        if (!list.contains(requestUri)) {
            String userNoA = request.getHeader("UserNoA");
            if (StringUtil.isNotEmpty(userNoA)) {
                UserRelevance userRelevance = new UserRelevance();
                userRelevance.setUserNOA(userNoA);
                String url="http://127.0.0.1:8088/userRelevance/queryList";
                String doPostJson = HttpClientUtil.doPostJson(url, JsonUtil.objectToJson(userRelevance));
                if (null!=doPostJson){
                    BaseResponse baseResponse = JsonUtil.jsonToObject(doPostJson,BaseResponse.class);
                    if (null != baseResponse && ReturnCodeEnum.CODE_1000.getCode().equals(baseResponse.getReturnCode()) && 1 == (baseResponse.getDataList().size())) {
                        UserRelevance userReleva =JsonUtil.jsonToObject( JsonUtil.objectToJson( baseResponse.getDataList().get(0)),UserRelevance.class);
                            if (null != userReleva && StringUtil.isNotEmpty(userReleva.getUserNOB())) {
                                Cookie cookie = new Cookie("UserNoB", userReleva.getUserNOB());
                                //cookie.setDomain(SystemConfig.getProperty("cookie.domain", request.getServerName()));//指定域
                                response.addCookie(cookie);
                                filterChain.doFilter(servletRequest, servletResponse);
                                return;
                            }
                    }
                }

            }
            Cookie[] cookies = request.getCookies();
            boolean label = true;
            if (null!=cookies){
                for (Cookie cookie : cookies) {
                    String cookieName = cookie.getName();
                    String cookieValue = cookie.getValue();
                    if ("UserNoB".equals(cookieName) && StringUtil.isNotEmpty(cookieValue)) {
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
