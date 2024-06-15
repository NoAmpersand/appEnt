package com.example.appent.exception;

public class DelegationExist extends Exception  {

    public DelegationExist() {
        super("La délégation existe déjà.");
    }

    public DelegationExist(String message) {
        super(message);
    }

}
