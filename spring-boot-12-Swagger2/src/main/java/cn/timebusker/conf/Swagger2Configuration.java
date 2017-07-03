package cn.timebusker.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 通过@Configuration注解，让Spring来加载该类配置。再通过@EnableSwagger2注解来启用Swagger2。
 * 再通过createRestApi函数创建Docket的Bean之后， apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
 * select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，
 * 本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API， 并产生文档内容（除了被@ApiIgnore指定的请求）。
 * 
 * 添加文档内容
 * 
 * 在完成了上述配置后，其实已经可以生产文档内容，但是这样的文档主要针对请求本身，
 * 而描述主要来源于函数等命名产生，对用户并不友好，我们通常需要自己增加一些说明来丰富文档内容。
 * 如:我们通过@ApiOperation注解来给API增加说明、
 * 通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
 * 
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

	// TODO http://localhost:8080/swagger-ui.html

	/**
	 * Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。
	 * 总体目标是使客户端和文件系统作为服务器以同样的速度来更新。 文件的方法，参数和模型紧密集成到服务器端的代码，允许API来始终保持同步。
	 * Swagger 让部署管理和使用功能强大的API从未如此简单。
	 * 
	 * 前后端分离开发 API文档非常明确 测试的时候不需要再使用URL输入浏览器的方式来访问Controller
	 * 传统的输入URL的测试方式对于post请求的传参比较麻烦（当然，可以使用postman这样的浏览器插件）
	 * spring-boot与swagger的集成简单的一逼
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
		           .apiInfo(apiInfo()).select()
		           .apis(RequestHandlerSelectors.basePackage("cn.timebusker"))
		           .paths(PathSelectors.any())
		           .build();
	}
	
	private ApiInfo apiInfo() {
		Contact contact = new Contact("timebusker", "timebusker@vip.qq.com", "576697722");
		return new ApiInfoBuilder()
		           .title("Spring Boot中使用Swagger2构建RESTful APIs")
		           .description("更多Spring Boot相关实践请关注：https://github.com/timebusker/spring-boot")
		           .termsOfServiceUrl("https://github.com/timebusker/spring-boot/tree/master/spring-boot-12-Swagger2/")
		           .contact(contact)
		           .version("2.0.0")
		           .build();
	}

}