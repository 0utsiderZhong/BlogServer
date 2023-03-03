package com.clock.project.setting.domain;

import lombok.Data;

/**
 * @className: Social
 * @description: 社交信息
 * @author: Clock
 * 11/19/19
 */
@Data
public class Social {
    /**
     * 社交地址
     */
    private String url;
    /**
     * 显示图片地址
     */
    private String imgUrl;
    /**
     * true 表示当前窗口打开
     */
    private Boolean target;
}
