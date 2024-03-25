package com.example.fortesextrahourapi.exceptions;

public class CustomTokenValidationException extends RuntimeException {

    //Retorna só mensagem
    public CustomTokenValidationException(String message) {
        super(message);
    }

    //Retorna mensagem e causa do erro
    public CustomTokenValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}