package com.clock.project.setting.domain;

import lombok.Data;

import java.util.List;

/**
 * @className: Abut
 * @description: about 的panel设置
 * @author: Clock
 * 11/19/19
 */
@Data
public class AboutPanelSetting {
    /**
     * 关于我背景
     */
    private String background;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 姓名
     */
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 社交网站信息
     */
    private List<Social> socialList;
}
