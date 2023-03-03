package com.clock;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @className: ClockBlogServletInitializer
 * @description: web容器中进行部署
 * @author: CLOCK
 */
public class ClockBlogServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ClockBlogApplication.class);
    }
}
