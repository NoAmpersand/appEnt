package com.example.appent.exception;

import org.springframework.http.HttpStatus;

public class EpreuveInexistante extends Exception{

    private HttpStatus httpStatus;

    public EpreuveInexistante(){
        this.httpStatus = HttpStatus.CONFLICT;
    };

    public EpreuveInexistante(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus(){
        return this.httpStatus;
    }
}