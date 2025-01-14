package com.clock.common.exception.user;

/**
 * @className: UserPasswordNotMatchException
 * @description: user not exist or password are not match
 * @author: Clock
 * 
 */
public class UserPasswordNotMatchException extends UserException {

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
