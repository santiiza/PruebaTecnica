package com.development.account.infraestructure.exception;

import org.springframework.http.HttpStatus;

public class CustomBalanceUnavailableException extends CustomException {
    public CustomBalanceUnavailableException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
