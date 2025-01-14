package com.clock.project.tool.controller;

import com.clock.framework.web.controller.BaseController;
import com.clock.framework.web.domain.AjaxResult;
import com.clock.framework.web.domain.BaseEntity;
import com.clock.framework.web.page.TableDataInfo;
import com.clock.project.tool.domain.QuartzJob;
import com.clock.project.tool.service.QuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: QuartzJobController
 * @description:
 * @author: Clock
 * 11/07/19
 */
@RestController
@RequestMapping("/tool/quartz")
public class QuartzJobController extends BaseController {

    @Autowired
    QuartzJobService quartzJobService;

    @GetMapping("/list")
    @PreAuthorize("@permissionService.hasPermission('tool:quartz:list')")
    public TableDataInfo list(QuartzJob quartzJob) {
        startPage();
        List<QuartzJob> quartzJobList = quartzJobService.selectQuartzJobList(quartzJob);
        return getDataTable(quartzJobList);
    }

    @PostMapping()
    @PreAuthorize("@permissionService.hasPermission('tool:quartz:add')")
    public AjaxResult add(@Validated @RequestBody QuartzJob quartzJob) {
        return toAjax(quartzJobService.insertQuartzJob(quartzJob));
    }

    @GetMapping("/{id}")
    @PreAuthorize("@permissionService.hasPermission('tool:quartz:query')")
    public AjaxResult get(@PathVariable Long id) {
        return AjaxResult.success(quartzJobService.selectQuartzJobById(id));
    }

    @PutMapping()
    @PreAuthorize("@permissionService.hasPermission('tool:quartz:edit')")
    public AjaxResult edit(@RequestBody @Validated(BaseEntity.Update.class) QuartzJob quartzJob) {
        return toAjax(quartzJobService.updateQuartzJob(quartzJob));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("@permissionService.hasPermission('tool:quartz:remove')")
    public AjaxResult delete(@PathVariable Long id) {
        return toAjax(quartzJobService.deleteQuartzJob(id));
    }

    @PutMapping("/exe/{id}")
    @PreAuthorize("@permissionService.hasPermission('tool:quartz:exec')")
    public AjaxResult execute(@PathVariable Long id) {
        quartzJobService.executeQuartzJobById(id);
        return AjaxResult.success();
    }

    @PutMapping("/status/{id}")
    @PreAuthorize("@permissionService.hasPermission('tool:quartz:changeStatus')")
    public AjaxResult updateQuartzJobStatus(@PathVariable Long id) {
        return toAjax(quartzJobService.updateQuartzJobStatus(id));
    }

}

