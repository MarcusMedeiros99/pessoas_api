package com.pessoas_api.pessoas_api.controllers.dto;

public class ErrorDTO {
    private String error;

    public ErrorDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
