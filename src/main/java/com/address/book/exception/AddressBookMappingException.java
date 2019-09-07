package com.address.book.exception;

/**
 * Created by tyronboyd on 7/9/19.
 */
public class AddressBookMappingException extends Exception {

    public AddressBookMappingException() {
    }

    public AddressBookMappingException(String message) {
        super(message);
    }

    public AddressBookMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressBookMappingException(Throwable cause) {
        super(cause);
    }

    public AddressBookMappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
