package com.example.appent.exception;

import org.springframework.http.HttpStatus;

public class OrganisateurAlreadyExistsException extends RuntimeException {
    private final HttpStatus httpStatus;

    public OrganisateurAlreadyExistsException() {
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public OrganisateurAlreadyExistsException(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public HttpStatus getStatus() {
        return this.httpStatus;
    }
}
