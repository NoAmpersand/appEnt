package com.example.appent.exception;

import org.springframework.http.HttpStatus;

public class OrganisateurExists extends Exception{

    private HttpStatus httpStatus;

    public OrganisateurExists(){
        this.httpStatus = HttpStatus.CONFLICT;
    };

    public OrganisateurExists(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus(){
        return this.httpStatus;
    }
}