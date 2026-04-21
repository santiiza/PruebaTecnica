package com.development.account.infraestructure.exception;

import org.springframework.http.HttpStatus;

public class CustomDifferentAccountException extends CustomException {
    public CustomDifferentAccountException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
