package com.clock.project.monitor.controller;

import com.clock.framework.web.controller.BaseController;
import com.clock.framework.web.domain.AjaxResult;
import com.clock.project.monitor.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: ServerController
 * @description: 服务器监控
 * @author: Clock
 * 
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController extends BaseController {
    @Autowired
    private ServerService serverService;

    @PreAuthorize("@permissionService.hasPermission('monitor:server:list')")
    @GetMapping()
    public AjaxResult getInfo() throws Exception {
        return AjaxResult.success(serverService.getServers());
    }
}
