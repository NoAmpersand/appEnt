package com.example.appent.exceptions;

import org.springframework.http.HttpStatus;

public class BilletNotFoundException extends Exception {
    private HttpStatus httpStatus;

    public BilletNotFoundException(){
        this.httpStatus = HttpStatus.CONFLICT;
    };

    public BilletNotFoundException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus(){
        return this.httpStatus;
    }
}
