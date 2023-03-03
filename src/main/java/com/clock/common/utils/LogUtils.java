package com.clock.common.utils;

/**
 * @className: LogUtils
 * @description: 处理并记录日志文件
 * @author: Clock
 * 
 */
public class LogUtils {
    private LogUtils() {
    }

    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
