package com.address.book.exception;

/**
 * Created by tyronboyd on 5/9/19.
 */
public class UpdateRepositoryException extends Exception {

    public UpdateRepositoryException() {
    }

    public UpdateRepositoryException(String message) {
        super(message);
    }

    public UpdateRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateRepositoryException(Throwable cause) {
        super(cause);
    }

    public UpdateRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
