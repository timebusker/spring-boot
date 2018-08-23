package com.timebusker.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建全局异常处理类：通过使用@ControllerAdvice定义统一的异常处理类，
 * 而不是在每个Controller中逐个定义。
 *
 * @DESC:CommonExceptionHandler
 * @author:timebusker
 * @date:2018/8/22
 */
@ControllerAdvice
public class CommonExceptionHandler {
    /**
     * 在@ControllerAdvice标记类中，根据抛出的具体Exception类型匹配
     * @ExceptionHandler中配置的异常类型来匹配错误映射和处理
     */

    /**
     * 统一错误页面
     */
    public static final String DEFAULT_ERROR_VIEW = "error";

    /**
     * 捕获自定义异常，返回json信息
     * @ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.html中
     */
    @ExceptionHandler(value = CommonException.class)
    public ModelAndView ErrorHandler(HttpServletRequest req, CommonException e) throws Exception {
        ModelAndView mv = new ModelAndView();
        ErrorMassage<CommonException> error = new ErrorMassage<>();
        error.setCode(404);
        error.setMessage("Not Found Exception......");
        error.setUrl(req.getRequestURL().toString());
        error.setData(e);
        mv.addObject(error);
        mv.setViewName(DEFAULT_ERROR_VIEW);
        return mv;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorMassage<Exception> ErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ErrorMassage<Exception> error = new ErrorMassage<Exception>();
        error.setCode(400);
        error.setMessage("系统异常");
        error.setUrl(req.getRequestURI());
        error.setData(e);
        return error;
    }
}
