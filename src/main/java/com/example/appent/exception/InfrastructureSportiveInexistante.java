package com.example.appent.exception;

import org.springframework.http.HttpStatus;

public class InfrastructureSportiveInexistante extends Exception {

    private final HttpStatus httpStatus;

    public InfrastructureSportiveInexistante() {
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public InfrastructureSportiveInexistante(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public HttpStatus getStatus() {
        return this.httpStatus;
    }
}