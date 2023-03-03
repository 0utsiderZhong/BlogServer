package com.clock.project.system.service.impl;

import com.clock.common.utils.ConvertUtils;
import com.clock.common.utils.SecurityUtils;
import com.clock.project.system.domain.Notice;
import com.clock.project.system.mapper.NoticeMapper;
import com.clock.project.system.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: NoticeServiceImpl
 * @description: 公告 服务层实现
 * @author: Clock
 * 
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public Notice selectNoticeById(Long noticeId) {
        return noticeMapper.selectNoticeById(noticeId);
    }

    @Override
    public List<Notice> selectNoticeList(Notice notice) {
        return noticeMapper.selectNoticeList(notice);
    }

    @Override
    public int insertNotice(Notice notice) {
        return noticeMapper.insertNotice(notice);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    @Override
    public int deleteNoticeByIds(String ids) {
        String loginUsername = SecurityUtils.getUsername();
        return noticeMapper.deleteNoticeByIds(ConvertUtils.toLongArray(ids), loginUsername);
    }
}
