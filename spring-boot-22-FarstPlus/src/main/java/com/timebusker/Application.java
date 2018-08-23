package com.timebusker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @DESC:Application
 * @author:timebusker
 * @date:2018/8/22
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
        System.err.println("\n**************************************\n");
        System.err.println("\t\t\t服务启动成功...");
        System.err.println("\n**************************************\n");
    }
}
