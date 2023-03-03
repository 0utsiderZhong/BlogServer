package com.clock.project.system.controller;

import com.clock.common.utils.SecurityUtils;
import com.clock.common.utils.ServletUtils;
import com.clock.framework.aspectj.lang.annotation.Log;
import com.clock.framework.aspectj.lang.enums.BusinessType;
import com.clock.framework.security.LoginUser;
import com.clock.framework.security.service.TokenService;
import com.clock.framework.web.controller.BaseController;
import com.clock.framework.web.domain.AjaxResult;
import com.clock.project.system.domain.SysUser;
import com.clock.project.system.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: ProfileController
 * @description: 个人信息 业务处理
 * @author: Clock
 * 
 */
@RestController
@RequestMapping("/system/user/profile")
public class ProfileController extends BaseController {
    private final UserService userService;

    private final TokenService tokenService;

    public ProfileController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    /**
     * 个人信息
     */
    @GetMapping
    public AjaxResult profile() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        AjaxResult ajax = AjaxResult.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
        return ajax;
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateProfile(@RequestBody SysUser user) {
        return toAjax(userService.updateUserProfile(user));
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String userName = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        return toAjax(userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)));
    }
}
