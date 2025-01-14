package com.clock.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.clock.common.constant.ConfigKey;
import com.clock.framework.aspectj.lang.annotation.Log;
import com.clock.framework.aspectj.lang.enums.BusinessType;
import com.clock.framework.web.controller.BaseController;
import com.clock.framework.web.domain.AjaxResult;
import com.clock.project.system.domain.AboutSetting;
import com.clock.project.system.domain.Config;
import com.clock.project.system.domain.EmailSetting;
import com.clock.project.system.domain.SiteSetting;
import com.clock.project.system.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: SettingController
 * @description:
 * @author: Clock
 * 01/08/20
 */
@RestController
@RequestMapping("system/setting")
public class SettingController extends BaseController {
    @Autowired
    ConfigService configService;

    @GetMapping("/about")
    @PreAuthorize("@permissionService.hasPermission('system:setting:about:query')")
    public AjaxResult about() {
        Config config = configService.selectConfigByKey(ConfigKey.CONFIG_KEY_ABOUT);
        if (config != null) {
            AboutSetting aboutSetting = JSON.parseObject(config.getConfigValue(), AboutSetting.class);
            return AjaxResult.success("获取成功", aboutSetting.getContent());
        }
        return AjaxResult.success(new AboutSetting());
    }

    @PutMapping("/about")
    @Log(title = "系统设置-关于", businessType = BusinessType.UPDATE)
    @PreAuthorize("@permissionService.hasPermission('system:setting:about:edit')")
    public AjaxResult editAbout(@RequestBody AboutSetting aboutSetting) {
        String jsonString = JSON.toJSONString(aboutSetting);
        Config config = new Config();
        config.setConfigKey(ConfigKey.CONFIG_KEY_ABOUT);
        config.setConfigValue(jsonString);
        return AjaxResult.success(configService.updateConfigByConfigKey(config));
    }

    @GetMapping("/siteSetting")
    @PreAuthorize("@permissionService.hasPermission('system:setting:siteSetting:query')")
    public AjaxResult siteSetting() {
        Config config = configService.selectConfigByKey(ConfigKey.CONFIG_KEY_SITE_SETTING);
        //convert to site setting
        if (config != null) {
            SiteSetting siteSetting = (SiteSetting) JSON.parse(config.getConfigValue());
            return AjaxResult.success(siteSetting);
        }
        return AjaxResult.success(new SiteSetting());
    }

    @PutMapping("siteSetting")
    @PreAuthorize("@permissionService.hasPermission('system:setting:siteSetting:edit')")
    @Log(title = "系统设置-网站设置", businessType = BusinessType.UPDATE)
    public AjaxResult editSiteSetting(@RequestBody SiteSetting siteSetting) {
        String jsonString = JSON.toJSONString(siteSetting);
        Config config = new Config();
        config.setConfigKey(ConfigKey.CONFIG_KEY_SITE_SETTING);
        config.setConfigValue(jsonString);
        return AjaxResult.success(configService.updateConfigByConfigKey(config));
    }

    @GetMapping("/emailSetting")
    @PreAuthorize("@permissionService.hasPermission('system:setting:emailSetting:query')")
    public AjaxResult emailSetting() {
        Config config = configService.selectConfigByKey(ConfigKey.CONFIG_KEY_EMAIL_SETTING);
        //convert to site setting
        if (config != null) {
            EmailSetting emailSetting = JSON.parseObject(config.getConfigValue(), EmailSetting.class);
            emailSetting.setPassword("*************************");
            return AjaxResult.success(emailSetting);
        }
        return AjaxResult.success(new EmailSetting());
    }

    @PutMapping("emailSetting")
    @Log(title = "系统设置-邮件设置", businessType = BusinessType.UPDATE)
    @PreAuthorize("@permissionService.hasPermission('system:setting:emailSetting:edit')")
    public AjaxResult editEmailSetting(@RequestBody EmailSetting emailSetting) {
        String jsonString = JSON.toJSONString(emailSetting);
        Config config = new Config();
        config.setConfigKey(ConfigKey.CONFIG_KEY_EMAIL_SETTING);
        config.setConfigValue(jsonString);
        return AjaxResult.success(configService.updateConfigByConfigKey(config));
    }
}
