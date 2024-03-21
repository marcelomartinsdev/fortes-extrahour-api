package com.example.fortesextrahourapi.exceptions;

public class CustomTokenCreationException extends RuntimeException {

    //Retorna só mensagem
    public CustomTokenCreationException(String message) {
        super(message);
    }

    //Retorna mensagem e causa do erro
    public CustomTokenCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}