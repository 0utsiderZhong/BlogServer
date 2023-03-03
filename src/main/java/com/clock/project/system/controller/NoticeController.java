package com.clock.project.system.controller;

import com.clock.common.utils.SecurityUtils;
import com.clock.framework.aspectj.lang.annotation.Log;
import com.clock.framework.aspectj.lang.enums.BusinessType;
import com.clock.framework.web.controller.BaseController;
import com.clock.framework.web.domain.AjaxResult;
import com.clock.framework.web.page.TableDataInfo;
import com.clock.project.system.domain.Notice;
import com.clock.project.system.service.NoticeService;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @className: NoticeController
 * @description: 公告 信息操作处理
 * @author: Clock
 * 
 */
@RestController
@RequestMapping("/system/notice")
public class NoticeController extends BaseController {
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    /**
     * 获取通知公告列表
     */
    @PreAuthorize("@permissionService.hasPermission('system:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(Notice notice) {
        startPage();
        List<Notice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @PreAuthorize("@permissionService.hasPermission('system:notice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(noticeService.selectNoticeById(id));
    }

    /**
     * 新增通知公告
     */
    @PreAuthorize("@permissionService.hasPermission('system:notice:add')")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Notice notice) {
        notice.setCreateBy(SecurityUtils.getUsername());
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @PreAuthorize("@permissionService.hasPermission('system:notice:edit')")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Notice notice) {
        notice.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     */
    @PreAuthorize("@permissionService.hasPermission('system:notice:remove')")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(noticeService.deleteNoticeByIds(ids));
    }
}
