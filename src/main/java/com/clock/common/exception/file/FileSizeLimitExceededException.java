package com.clock.common.exception.file;

/**
 * @className: FileSizeLimitExceededException
 * @description: if the size of this file are gt than the maxSize,will throw this exception
 * @author: Clock
 */
public class FileSizeLimitExceededException extends FileException {

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", new Object[]{defaultMaxSize});
    }
}
