package com.example.fortesextrahourapi.exceptions;

import java.util.HashMap;
import java.util.Map;

import lombok.Setter;
import org.springframework.http.HttpStatus;

public class FortesException extends RuntimeException {
    private final Map<String, String> errors;

    @Setter
    private HttpStatus status;

    public FortesException() {
        this.errors = new HashMap<>();
    }

    public FortesException(String descricao, HttpStatus status) {
        this();
        this.status = status;
        add("status", String.valueOf(status.value()));
        add("mensagem", descricao);
    }


    public HttpStatus getStatus() {
        return this.status;
    }

    public void add(String erro, String descricao) {
        this.errors.put(erro, descricao);
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
