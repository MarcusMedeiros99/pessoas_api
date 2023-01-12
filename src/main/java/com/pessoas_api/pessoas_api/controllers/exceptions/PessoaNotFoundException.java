package com.pessoas_api.pessoas_api.controllers.exceptions;

public class PessoaNotFoundException extends IllegalArgumentException {
    private String message;

    public PessoaNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
