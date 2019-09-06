package com.address.book.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tyronboyd on 7/9/19.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class HttpBadRequestException extends RuntimeException {

    private String errMessage;

    public HttpBadRequestException(String errMessage) {
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