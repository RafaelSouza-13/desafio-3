package com.rafa.desafio3.services.exceptions;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(String msg){
        super(msg);
    }
}
