package com.example.appent.exception;

public class DelegationNotExisting  extends  Exception{

    public DelegationNotExisting(){
        super("La délégation n'existe pas .");
    }

    public DelegationNotExisting(String message) {
        super(message);
    }
}
