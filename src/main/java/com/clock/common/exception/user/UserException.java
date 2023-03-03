package com.clock.common.exception.user;

import com.clock.common.exception.BaseException;

/**
 * @className: UserException
 * @description: user exception
 * @author: Clock
 * 
 */
public class UserException extends BaseException {

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
