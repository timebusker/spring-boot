package com.timebusker.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @DESC:LoginFilter
 * @author:timebusker
 * @date:2018/8/23
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        String url = httpRequest.getRequestURI();
        String USER_ACCOUNT = (String) session.getAttribute("USER_ACCOUNT");
//        if (url.indexOf("login") > 0 || url.indexOf("authcode") >= 0) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//        if (StringUtils.isNotBlank(USER_ACCOUNT)) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            httpResponse.sendRedirect("/sys/tologin");
//        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
