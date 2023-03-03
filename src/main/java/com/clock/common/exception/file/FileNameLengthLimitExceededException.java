package com.clock.common.exception.file;

/**
 * @className: FileNameLengthLimitExceededException
 * @description: if the name of file is too long,will throw this exception
 * @author: Clock
 */
public class FileNameLengthLimitExceededException extends FileException {

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("upload.filename.exceed.length", new Object[]{defaultFileNameLength});
    }
}
