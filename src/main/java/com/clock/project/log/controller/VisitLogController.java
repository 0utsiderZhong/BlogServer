package com.clock.project.log.controller;

import com.clock.framework.web.controller.BaseController;
import com.clock.framework.web.domain.AjaxResult;
import com.clock.framework.web.page.TableDataInfo;
import com.clock.project.log.domain.VisitLog;
import com.clock.project.log.service.VisitLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: VisitLogController
 * @description: 前台访问日志记录
 * @author: Clock
 * 
 */
@RestController
@RequestMapping("/log/visitLog")
public class VisitLogController extends BaseController {
    private final VisitLogService visitLogService;

    public VisitLogController(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:visitLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(VisitLog visitLog) {
        startPage();
        List<VisitLog> list = visitLogService.selectVisitLogList(visitLog);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:visitLog:remove')")
    @DeleteMapping("{ids}")
    public AjaxResult deleteLoginLog(@PathVariable String ids) {
        return toAjax(visitLogService.deleteVisitLogByIds(ids));
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:visitLog:remove')")
    @DeleteMapping("/clean")
    public AjaxResult cleanVisitLog() {
        visitLogService.cleanVisitLog();
        return AjaxResult.success();
    }
}
