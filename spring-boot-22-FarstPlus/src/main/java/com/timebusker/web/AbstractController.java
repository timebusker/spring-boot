package com.timebusker.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.*;

/**
 * @DESC:AbstractController:抽象类
 * @author:timebusker
 * @date:2018/8/22
 */
public abstract class AbstractController implements ApplicationContextAware {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public static ApplicationContext applicationContext;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    /**
     * 在方法定义上使用@ModelAttribute
     * 在SpringMVC调用任何方法前，被@ModelAttribute注解的方法都会先被依次调用，
     * 并且这些注解方法的返回值都会被添加到模型中。
     *
     * @param request
     * @param response
     * @param session
     */
    @ModelAttribute
    public void before(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        this.request = request;
        this.response = response;
        this.session = session;
        if ((request.getRequestURI().indexOf("login") < 0 || request.getRequestURI().indexOf("authcode") < 0) && null == session.getAttribute("USER_ACCOUNT")) {
            try {
                this.login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 指定到登录页面
     *
     * @return
     */
    @RequestMapping(value = {"/"})
    public String login() {
        return "login";
    }

    /**
     * 手动 IoC 上下文信息
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AbstractController.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    public static Class<? extends Object> getType(String name) {
        return applicationContext.getType(name);
    }

}
