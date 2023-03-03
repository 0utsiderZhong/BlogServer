package com.clock.project.system.service.impl;

import com.clock.common.constant.UserConstants;
import com.clock.common.exception.CustomException;
import com.clock.common.utils.ConvertUtils;
import com.clock.common.utils.SecurityUtils;
import com.clock.common.utils.StringUtils;
import com.clock.project.system.domain.Role;
import com.clock.project.system.domain.RoleMenu;
import com.clock.project.system.mapper.RoleMapper;
import com.clock.project.system.mapper.RoleMenuMapper;
import com.clock.project.system.mapper.UserRoleMapper;
import com.clock.project.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @className: RoleServiceImpl
 * @description: 角色 业务层处理
 * @author: Clock
 * 
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> selectRoleList(Role role) {
        return roleMapper.selectRoleList(role);
    }

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<Role> perms = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (Role perm : perms) {
            if (StringUtils.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Role> selectRoleAll() {
        return roleMapper.selectRoleAll();
    }

    @Override
    public List<Integer> selectRoleListByUserId(Long userId) {
        return roleMapper.selectRoleListByUserId(userId);
    }

    @Override
    public Role selectRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    @Override
    public String checkRoleNameUnique(Role role) {
        Long roleId = StringUtils.isNull(role.getId()) ? -1L : role.getId();
        Role info = roleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != roleId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkRoleKeyUnique(Role role) {
        Long roleId = StringUtils.isNull(role.getId()) ? -1L : role.getId();
        Role info = roleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != roleId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public void checkRoleAllowed(Role role) {
        if (StringUtils.isNotNull(role.getId()) && role.isAdmin()) {
            throw new CustomException("不允许操作超级管理员角色");
        }
    }

    @Override
    @Transactional
    public int insertRole(Role role) {
        // 新增角色信息
        roleMapper.insertRole(role);
        return insertRoleMenu(role);
    }

    @Override
    @Transactional
    public int updateRole(Role role) {
        // 修改角色信息
        roleMapper.updateRole(role);
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(role.getId());
        return insertRoleMenu(role);
    }

    @Override
    public int updateRoleStatus(Role role) {
        return roleMapper.updateRole(role);
    }


    @Override
    @Transactional
    public int authDataScope(Role role) {
        // 修改角色信息
        roleMapper.updateRole(role);
        return 1;
    }

    /**
     * 新增角色菜单信息
     *
     * @param role 角色对象
     */
    public int insertRoleMenu(Role role) {
        int rows = 1;
        // 新增用户与角色管理
        List<RoleMenu> list = new ArrayList<>();
        for (Long menuId : role.getMenuIds()) {
            RoleMenu rm = new RoleMenu();
            rm.setRoleId(role.getId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (!list.isEmpty()) {
            rows = roleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }

    @Override
    public int deleteRoleByIds(String ids) {
        Long[] roleIds = ConvertUtils.toLongArray(ids);
        for (Long roleId : roleIds) {
            checkRoleAllowed(new Role(roleId));
            Role role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0) {
                throw new CustomException(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        String loginUsername = SecurityUtils.getUsername();
        return roleMapper.deleteRoleByIds(roleIds, loginUsername);
    }

    public int countUserRoleByRoleId(Long roleId) {
        return userRoleMapper.countUserRoleByRoleId(roleId);
    }
}
