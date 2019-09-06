package com.address.book.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tyronboyd on 7/9/19.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class HttpNotFoundException extends RuntimeException {

    private String errMessage;

    public HttpNotFoundException(String errMessage) {
        super();
        this.setErrMessage(errMessage);
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

}
