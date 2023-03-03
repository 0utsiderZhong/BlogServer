package com.clock.common.exception.file;

import com.clock.common.exception.BaseException;

/**
 * @className: FileException
 * @description: file exception class
 * @author: Clock
 */
public class FileException extends BaseException {

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
