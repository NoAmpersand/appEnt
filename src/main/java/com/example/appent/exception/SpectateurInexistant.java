package com.example.appent.exception;

import org.springframework.http.HttpStatus;

public class SpectateurInexistant extends RuntimeException{

    private HttpStatus httpStatus;

    public SpectateurInexistant(){
        this.httpStatus = HttpStatus.CONFLICT;
    };

    public SpectateurInexistant(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus(){
        return this.httpStatus;
    }

}



