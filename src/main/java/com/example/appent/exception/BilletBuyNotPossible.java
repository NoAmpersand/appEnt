package com.example.appent.exception;

import org.springframework.http.HttpStatus;

public class BilletBuyNotPossible extends Exception{
    private HttpStatus httpStatus;

    public BilletBuyNotPossible(){
        this.httpStatus = HttpStatus.CONFLICT;
    };

    public BilletBuyNotPossible(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus(){
        return this.httpStatus;
    }
}




