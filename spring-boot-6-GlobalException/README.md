----
## [spring-boot-6-GlobalException 统一异常捕获处理](https://github.com/timebusker/spring-boot/tree/master/spring-boot-6-GlobalException/)

### 项目阐述
   ![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-6-GlobalException/error.png?raw=true)

   我们在做Web应用的时候，请求处理过程中发生错误是非常常见的情况。Spring Boot提供了一个默认的映射：**/error**，
   当处理中抛出异常之后，会转到该请求中处理，并且该请求有一个全局的错误页面用来展示异常内容。
   
   虽然，Spring Boot中实现了默认的error映射，但是在实际应用中，上面你的错误页面对用户来说并不够友好，
   我们通常需要去实现我们自己的异常提示。
 
 + #### 创建全局异常处理类
   通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义。
   @ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.html中。
   
```java
package cn.timebusker.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.timebusker.exception.ErrorMassage;
import cn.timebusker.exception.MineException;

/**
 * 创建全局异常处理类：通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义。
 * 
 * @ControllerAdvice统一定义不同Exception映射到不同错误处理页面
 */
@ControllerAdvice
public class GlobalExceptionController {

	/**
	 * 在@ControllerAdvice类中，根据抛出的具体Exception类型匹配@ExceptionHandler中配置的异常类型来匹配错误映射和处理
	 */
	public static final String DEFAULT_ERROR_VIEW = "error";

	/**
	 * 捕获自定义异常，返回json信息
	 */
	@ExceptionHandler(value = MineException.class)
	@ResponseBody
	public ErrorMassage<Object> ErrorHandler(HttpServletRequest req, MineException e) throws Exception {
		ErrorMassage<Object> error = new ErrorMassage<>();
		error.setCode(404);
		error.setMessage("Not Found Exception ！！！");
		error.setUrl(req.getRequestURL().toString());
		error.setData(e);
		return error;
	}
	
	/**
	 * @ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.html中
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView ErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		ModelAndView mav = new ModelAndView();
		ErrorMassage<Exception> error = new ErrorMassage<Exception>();
		error.setCode(400);
		error.setMessage("系统异常");
		error.setUrl(req.getRequestURI());
		error.setData(e);
		mav.addObject(error);
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
}
```
 + #### 实现**error.html**
	  在templates目录下创建error.html，将请求的URL和Exception对象的message输出。
```html
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8" />
<title>统一异常处理</title>
</head>
<body>
	<h1>errorMassage Handler</h1>
	<hr>1:错误编码</hr>
	<div th:text="${errorMassage.code}"></div>
	<hr>2：错误消息</hr>
	<div th:text="${errorMassage.message}"></div>
	<hr>3：错误的URL</hr>
	<div th:text="${errorMassage.url}"></div>
	<hr>4：异常信息</hr>
	<div th:text="${errorMassage.data}"></div>
</body>
</html>
```
----