package cn.timebusker;

/**
 * 测试Spring Boot自定义Starter
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        // devtools：是spring boot的一个热部署工具
        //设置 spring.devtools.restart.enabled 属性为false，可以关闭该特性.
        //System.setProperty("spring.devtools.restart.enabled","false");
        // 启动Sprign Boot
        SpringApplication.run(App.class, args);
    }
}
