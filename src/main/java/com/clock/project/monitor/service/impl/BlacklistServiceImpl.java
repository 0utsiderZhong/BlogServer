package com.clock.project.monitor.service.impl;

import com.clock.common.utils.ConvertUtils;
import com.clock.common.utils.SecurityUtils;
import com.clock.project.monitor.domain.Blacklist;
import com.clock.project.monitor.mapper.BlacklistMapper;
import com.clock.project.monitor.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: BlacklistServiceImpl
 * @description:
 * @author: Clockb
 * 10/24/19
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {

    @Autowired
    BlacklistMapper blacklistMapper;

    @Override
    public int deleteBlacklistByIds(String ids) {
        return blacklistMapper.deleteBlacklistByIds(ConvertUtils.toStrArray(ids), SecurityUtils.getUsername());
    }

    @Override
    public int updateBlacklist(Blacklist blacklist) {
        return blacklistMapper.updateBlacklist(blacklist);
    }

    @Override
    public int insertBlacklist(Blacklist blacklist) {
        return blacklistMapper.insertBlacklist(blacklist);
    }

    @Override
    public Blacklist selectBlacklistById(Long id) {
        return blacklistMapper.selectBlacklistById(id);
    }

    @Override
    public List<Blacklist> selectBlacklistList(Blacklist blacklist) {
        return blacklistMapper.selectBlacklistList(blacklist);
    }
}
