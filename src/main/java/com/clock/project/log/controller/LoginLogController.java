package com.clock.project.log.controller;

import com.clock.common.utils.SecurityUtils;
import com.clock.framework.web.controller.BaseController;
import com.clock.framework.web.domain.AjaxResult;
import com.clock.framework.web.page.TableDataInfo;
import com.clock.project.log.domain.LoginLog;
import com.clock.project.log.service.LoginLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: LoginLogController
 * @description: 系统访问记录
 * @author: Clock
 * 
 */
@RestController
@RequestMapping("/log/loginLog")
public class LoginLogController extends BaseController {
    private final LoginLogService loginLogService;

    public LoginLogController(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:loginLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(LoginLog loginLog) {
        startPage();
        List<LoginLog> list = loginLogService.selectLoginLogList(loginLog);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:loginLog:query')")
    @GetMapping()
    public TableDataInfo queryCurrentUserLoginLog(LoginLog loginLog) {
        startPage();
        loginLog.setUserName(SecurityUtils.getUsername());
        List<LoginLog> list = loginLogService.selectLoginLogList(loginLog);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:loginLog:remove')")
    @DeleteMapping("{ids}")
    public AjaxResult deleteLoginLog(@PathVariable String ids) {
        return toAjax(loginLogService.deleteLoginLogByIds(ids));
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:loginLog:remove')")
    @DeleteMapping("/clean")
    public AjaxResult cleanLoginLog() {
        loginLogService.cleanLoginLog();
        return AjaxResult.success();
    }
}
