package com.example.appent.exception;

import org.springframework.http.HttpStatus;

public class OrganisateurNotExisting extends RuntimeException{

    private final HttpStatus httpStatus;

    public OrganisateurNotExisting() {
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public OrganisateurNotExisting(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public HttpStatus getStatus() {
        return this.httpStatus;
    }
}



