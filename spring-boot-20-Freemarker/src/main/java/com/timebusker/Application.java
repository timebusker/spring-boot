package com.timebusker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @DESC:Application
 * @author:timebusker
 * @date:2018/6/23
 */
@SpringBootApplication
public class Application {
    private final static Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LOG.info("启动应用成功...");
        LOG.info("设置参数是：" + args.toString());
    }
}
