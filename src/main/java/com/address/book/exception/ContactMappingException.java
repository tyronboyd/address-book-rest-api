package com.address.book.exception;

/**
 * Created by tyronboyd on 6/9/19.
 */
public class ContactMappingException extends Exception {

    public ContactMappingException() {
    }

    public ContactMappingException(String message) {
        super(message);
    }

    public ContactMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactMappingException(Throwable cause) {
        super(cause);
    }

    public ContactMappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
