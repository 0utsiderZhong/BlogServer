package com.clock.project.log.service.impl;

import com.clock.common.utils.ConvertUtils;
import com.clock.common.utils.SecurityUtils;
import com.clock.project.log.domain.VisitLog;
import com.clock.project.log.mapper.VisitLogMapper;
import com.clock.project.log.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: VisitLogServiceImpl
 * @description:
 * @author: Clock
 * 2019/11/30
 */
@Service
public class VisitLogServiceImpl implements VisitLogService {
    @Autowired
    VisitLogMapper visitLogMapper;

    @Override
    public List<VisitLog> selectVisitLogList(VisitLog visitLog) {
        return visitLogMapper.selectVisitLogList(visitLog);
    }

    @Override
    public int deleteVisitLogByIds(String ids) {
        String username = SecurityUtils.getUsername();
        return visitLogMapper.deleteVisitLogByIds(ConvertUtils.toLongArray(ids), username);
    }

    @Override
    public void cleanVisitLog() {
        String username = SecurityUtils.getUsername();
        visitLogMapper.cleanVisitLog(username);
    }

    @Override
    public int insertVisitLog(VisitLog visitLog) {
        return visitLogMapper.insertVisitLog(visitLog);
    }

}
