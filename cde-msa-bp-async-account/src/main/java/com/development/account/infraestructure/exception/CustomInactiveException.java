package com.development.account.infraestructure.exception;

import org.springframework.http.HttpStatus;

public class CustomInactiveException extends CustomException {
    public CustomInactiveException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
