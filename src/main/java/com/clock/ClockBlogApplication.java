package com.clock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className: ClockBlogApplication
 * @description: 启动程序
 * @author: clock
 */
@SpringBootApplication
public class ClockBlogApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ClockBlogApplication.class, args);
    }
}
