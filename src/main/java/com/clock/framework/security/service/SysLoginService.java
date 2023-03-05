package com.clock.framework.security.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.clock.common.constant.Constants;
import com.clock.common.exception.CustomException;
import com.clock.common.exception.user.CaptchaException;
import com.clock.common.exception.user.CaptchaExpireException;
import com.clock.common.exception.user.UserPasswordNotMatchException;
import com.clock.common.utils.MessageUtils;
import com.clock.framework.manager.AsyncManager;
import com.clock.framework.manager.factory.AsyncFactory;
import com.clock.framework.redis.RedisCacheService;
import com.clock.framework.security.LoginUser;

/**
 * @className: SysLoginService
 * @description: 登录校验方法
 * @author: Clock
 * 
 */
@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCacheService redisCacheService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code  验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCacheService.getCacheObject(verifyKey);
        redisCacheService.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLoginLog(username, Constants.FAILED, MessageUtils.message("user.captcha.error")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLoginLog(username, Constants.FAILED, MessageUtils.message("user.captcha.expire")));
            throw new CaptchaException();
        }
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLoginLog(username, Constants.FAILED, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLoginLog(username, Constants.FAILED, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLoginLog(username, Constants.SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }
}
