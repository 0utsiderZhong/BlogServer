package com.clock.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @className: ClockBlogConfig
 * @description: get project setting
 * @author: Clock
 * 
 */
@Component
@ConfigurationProperties(prefix = "clock")
public class ClockBlogConfig {
    /**
     * application name
     */
    private String name;

    /**
     * application version
     */
    private String version;

    /**
     * copyright
     */
    private String copyrightYear;

    /**
     * upload url
     */
    private static String profile;

    /**
     * enable address get
     */
    private static boolean addressEnabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        ClockBlogConfig.profile = profile;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        ClockBlogConfig.addressEnabled = addressEnabled;
    }
}
