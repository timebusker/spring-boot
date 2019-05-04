package cn.timebusker.security.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;


/**
 * 抽象类GenericFilterBean实现了javax.servlet.Filter、org.springframework.beans.factory.BeanNameAware、org.springframework.context.EnvironmentAware、org.springframework.web.context.ServletContextAware、org.springframework.beans.factory.InitializingBean和org.springframework.beans.factory.DisposableBean五个接口，作用如下：
 * <p>
 * (1) Filter，实现过滤器；
 * <p>
 * (2) BeanNameAware，实现该接口的setBeanName方法，便于Bean管理器生成Bean；
 * <p>
 * (3) EnvironmentAware，实现该接口的setEnvironment方法，指明该Bean运行的环境；
 * <p>
 * (4) ServletContextAware，实现该接口的setServletContextAware方法，指明上下文；
 * <p>
 * (5) InitializingBean，实现该接口的afterPropertiesSet方法，指明设置属性生的操作；
 * <p>
 * (6) DisposableBean，实现该接口的destroy方法，用于回收资源。
 * <p>
 * GenericFilterBean的工作流程是：init-doFilter-destory，其中的init和destory在该类中实现，doFilter在具体实现类中实现。
 * <p>
 * GenericFilterBean中包含一个内部私有类FilterConfigPropertyValues，主要用于将web.xml中定义的init-param的值取出。
 */
public class AfterCsrfFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("This is a filter after CsrfFilter.");
        // 继续调用 Filter 链
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
