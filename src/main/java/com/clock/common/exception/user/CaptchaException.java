package com.clock.common.exception.user;

/**
 * @className: CaptchaException
 * @description: if captcha error will throw this exception
 * @author: Clock
 * 
 */
public class CaptchaException extends UserException {

    public CaptchaException() {
        super("user.captcha.error", null);
    }
}
