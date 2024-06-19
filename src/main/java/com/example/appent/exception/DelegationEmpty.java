package com.example.appent.exception;

public class DelegationEmpty extends Exception  {

    public DelegationEmpty() {
        super("La table délégation est vide.");
    }

    public DelegationEmpty(String message) {
        super(message);
    }

}
