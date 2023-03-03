package com.clock.project.tool.service.impl;

import com.clock.common.exception.CustomException;
import com.clock.common.utils.SecurityUtils;
import com.clock.project.tool.domain.QuartzJob;
import com.clock.project.tool.mapper.QuartzJobMapper;
import com.clock.project.tool.service.QuartzJobService;
import com.clock.project.tool.utils.QuartzManage;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @className: QuartzServiceImpl
 * @description:
 * @author: Clock
 * 11/07/19
 */
@Service
public class QuartzJobServiceImpl implements QuartzJobService {

    @Autowired
    QuartzJobMapper quartzJobMapper;

    @Autowired
    QuartzManage quartzManage;

    @Override
    public int insertQuartzJob(QuartzJob quartzJob) {
        if (!CronExpression.isValidExpression(quartzJob.getCronExpression())) {
            throw new CustomException("Cron表达式错误");
        }
        quartzManage.addJob(quartzJob);
        return quartzJobMapper.insertQuartzJob(quartzJob);
    }

    @Override
    public int updateQuartzJob(QuartzJob quartzJob) {
        if (!CronExpression.isValidExpression(quartzJob.getCronExpression())) {
            throw new CustomException("Cron表达式错误");
        }
        quartzManage.updateJobCron(quartzJob);
        return quartzJobMapper.updateQuartzJob(quartzJob);
    }

    @Override
    public int deleteQuartzJob(Long id) {
        QuartzJob quartzJob = quartzJobMapper.selectQuartzJobById(id);
        if (Objects.isNull(quartzJob)) {
            throw new CustomException("当前任务不存在");
        }
        quartzManage.deleteJob(quartzJob);
        String username = SecurityUtils.getUsername();
        return quartzJobMapper.deleteQuartzJobById(id, username);
    }

    @Override
    public void executeQuartzJobById(Long id) {
        QuartzJob quartzJob = quartzJobMapper.selectQuartzJobById(id);
        if (Objects.isNull(quartzJob)) {
            throw new CustomException("当前任务不存在");
        }
        quartzManage.runAJobNow(quartzJob);
    }

    @Override
    public int updateQuartzJobStatus(Long id) {
        QuartzJob quartzJob = quartzJobMapper.selectQuartzJobById(id);
        if (Objects.isNull(quartzJob)) {
            throw new CustomException("当前任务不存在");
        }
        //如果当前为运行状态
        if (quartzJob.getStatus().booleanValue()) {
            quartzManage.pauseJob(quartzJob);
        } else {
            quartzManage.resumeJob(quartzJob);
        }
        quartzJob.setStatus(!quartzJob.getStatus());
        return quartzJobMapper.updateQuartzJob(quartzJob);
    }

    @Override
    public List<QuartzJob> selectQuartzJobList(QuartzJob quartzJob) {
        return quartzJobMapper.selectQuartzJobList(quartzJob);
    }

    @Override
    public QuartzJob selectQuartzJobById(Long id) {
        return quartzJobMapper.selectQuartzJobById(id);
    }

    @Override
    public List<QuartzJob> selectRunningQuartzJobList() {
        return quartzJobMapper.selectRunningQuartzJobList();
    }
}
