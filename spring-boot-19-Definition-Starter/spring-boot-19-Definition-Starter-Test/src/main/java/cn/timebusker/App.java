package cn.timebusker;

import cn.timebusker.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 测试Spring Boot自定义Starter
 */
@SpringBootApplication
@RestController
public class App {

    public static void main(String[] args) {
        // devtools：是spring boot的一个热部署工具
        //设置 spring.devtools.restart.enabled 属性为false，可以关闭该特性.
        //System.setProperty("spring.devtools.restart.enabled","false");
        // 启动Sprign Boot
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private CalculateService calculateService;

    /**
     *  http://127.0.0.1/add?v1=3&v2=5
     * @param v1
     * @param v2
     * @return
     */
    @RequestMapping("/add")
    public double add(double v1, double v2) {
        return calculateService.add(v1, v2);
    }

    /**
     * http://127.0.0.1/sub?v1=3&v2=5
     * @param v1
     * @param v2
     * @return
     */
    @RequestMapping("/sub")
    public double sub(double v1, double v2) {
        return calculateService.sub(v1, v2);
    }

    /**
     * http://127.0.0.1/div?v1=3&v2=5
     * @param v1
     * @param v2
     * @return
     */
    @RequestMapping("/div")
    public double div(double v1, double v2) {
        return calculateService.div(v1, v2);
    }

    /**
     * http://127.0.0.1/mul?v1=3&v2=5
     * @param v1
     * @param v2
     * @return
     */
    @RequestMapping("/mul")
    public double mul(double v1, double v2) {
        return calculateService.mul(v1, v2);
    }


    /**
     * http://127.0.0.1/setScale?v=3.454656&scale=3
     * @param v
     * @param scale
     * @return
     */
    @RequestMapping("/setScale")
    public double setScale(double v, int scale) {
        return calculateService.setScale(v, scale);
    }

    /**
     * http://127.0.0.1/setScale2?v=3.454656
     * @param v
     * @return
     */
    @RequestMapping("/setScale2")
    public double setScale(double v) {
        return calculateService.setScale(v);
    }
}
