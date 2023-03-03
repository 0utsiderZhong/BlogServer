package com.clock.common.exception.user;

/**
 * @className: CaptchaExpireException
 * @description: if captcha is expired, will throw this exception
 * @author: Clock
 * 
 */
public class CaptchaExpireException extends UserException {


    public CaptchaExpireException() {
        super("user.captcha.expire", null);
    }
}
