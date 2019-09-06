package com.address.book.exception.http;

/**
 * Created by tyronboyd on 7/9/19.
 */
public class HttpErrorResponse {

    public HttpError getError() {
        return error;
    }

    public void setError(HttpError error) {
        this.error = error;
    }

    private HttpError error;
}
