package com.clock.framework.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @className: ShutdownManager
 * @description: Ensure that the background thread can be closed when the application exits
 * @author: Clock
 * 
 */
@Component
@Slf4j
public class ShutdownManager {

    @PreDestroy
    public void destroy() {
        shutdownAsyncManager();
    }

    /**
     * 停止异步执行任务
     */
    private void shutdownAsyncManager() {
        try {
            log.info("close thread pool");
            AsyncManager.me().shutdown();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
