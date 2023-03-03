package com.clock.project.monitor.controller;

import com.clock.common.constant.Constants;
import com.clock.common.utils.StringUtils;
import com.clock.framework.aspectj.lang.annotation.Log;
import com.clock.framework.aspectj.lang.enums.BusinessType;
import com.clock.framework.redis.RedisCacheService;
import com.clock.framework.security.LoginUser;
import com.clock.framework.web.controller.BaseController;
import com.clock.framework.web.domain.AjaxResult;
import com.clock.framework.web.page.TableDataInfo;
import com.clock.project.monitor.domain.UserOnline;
import com.clock.project.system.service.UserOnlineService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @className: UserOnlineController
 * @description: 在线用户监控
 * @author: Clock
 * 
 */
@RestController
@RequestMapping("/monitor/online")
public class UserOnlineController extends BaseController {
    private final UserOnlineService userOnlineService;

    private final RedisCacheService redisCacheService;

    public UserOnlineController(UserOnlineService userOnlineService, RedisCacheService redisCacheService) {
        this.userOnlineService = userOnlineService;
        this.redisCacheService = redisCacheService;
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:online:list')")
    @GetMapping("/list")
    public TableDataInfo list(String ip, String userName) {
        Collection<String> keys = redisCacheService.keys(Constants.LOGIN_TOKEN_KEY + "*");
        List<UserOnline> userOnlineList = new ArrayList<>();
        for (String key : keys) {
            LoginUser user = redisCacheService.getCacheObject(key);
            if (StringUtils.isNotEmpty(ip) && StringUtils.isNotEmpty(userName)) {
                if (StringUtils.equals(ip, user.getIp()) && StringUtils.equals(userName, user.getUsername())) {
                    userOnlineList.add(userOnlineService.selectOnlineByInfo(ip, userName, user));
                }
            } else if (StringUtils.isNotEmpty(ip)) {
                if (StringUtils.equals(ip, user.getIp())) {
                    userOnlineList.add(userOnlineService.selectOnlineByIpaddr(ip, user));
                }
            } else if (StringUtils.isNotEmpty(userName) && StringUtils.isNotNull(user.getUser())) {
                if (StringUtils.equals(userName, user.getUsername())) {
                    userOnlineList.add(userOnlineService.selectOnlineByUserName(userName, user));
                }
            } else {
                userOnlineList.add(userOnlineService.loginUserToUserOnline(user));
            }
        }
        Collections.reverse(userOnlineList);
        userOnlineList.removeAll(Collections.singleton(null));
        return getDataTable(userOnlineList);
    }

    /**
     * 强退用户
     */
    @PreAuthorize("@permissionService.hasPermission('monitor:online:forceLogout')")
    @Log(title = "在线用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tokenId}")
    public AjaxResult forceLogout(@PathVariable String tokenId) {
        redisCacheService.deleteObject(Constants.LOGIN_TOKEN_KEY + tokenId);
        return AjaxResult.success();
    }
}
