package com.clock.project.log.controller;

import com.clock.framework.web.controller.BaseController;
import com.clock.framework.web.domain.AjaxResult;
import com.clock.framework.web.page.TableDataInfo;
import com.clock.project.log.domain.OperateLog;
import com.clock.project.log.service.OperateLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: OperateLogController
 * @description: 操作日志记录
 * @author: Clock
 * 
 */
@RestController
@RequestMapping("/log/operateLog")
public class OperateLogController extends BaseController {
    private final OperateLogService operateLogService;

    public OperateLogController(OperateLogService operateLogService) {
        this.operateLogService = operateLogService;
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:operateLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(OperateLog operateLog) {
        startPage();
        List<OperateLog> list = operateLogService.selectOperateLogList(operateLog);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:operateLog:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult deleteQuartzJobLogByIds(@PathVariable String ids) {
        return toAjax(operateLogService.deleteOperateLogByIds(ids));
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:operateLog:remove')")
    @DeleteMapping("/clean")
    public AjaxResult cleanQuartzJobLog() {
        operateLogService.cleanOperateLog();
        return AjaxResult.success();
    }
}
