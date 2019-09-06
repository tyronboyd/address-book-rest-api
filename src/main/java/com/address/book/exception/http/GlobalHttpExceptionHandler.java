package com.address.book.exception.http;

import com.address.book.exception.NotFoundException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tyronboyd on 7/9/19.
 */
@ControllerAdvice
public class GlobalHttpExceptionHandler {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalHttpExceptionHandler.class);

    @ExceptionHandler(HttpBadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public HttpErrorResponse handleConflict(HttpBadRequestException ex) {
        return this.initHttpErrorResponse(ex, ex.getErrMessage(), "400");
    }

    @ExceptionHandler(HttpNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public HttpErrorResponse handleConflict(HttpNotFoundException ex) {
        return this.initHttpErrorResponse(ex, ex.getErrMessage(), "404");
    }

    private HttpErrorResponse initHttpErrorResponse(Exception e, String errMessage, String code) {
        HttpErrorResponse httpErrorResponse = new HttpErrorResponse();
        HttpError error = new HttpError();
        error.setMessage(errMessage);
        error.setErrorCode(code);
        httpErrorResponse.setError(error);

        logger.error("error message: " + errMessage, e);
        return httpErrorResponse;
    }
}