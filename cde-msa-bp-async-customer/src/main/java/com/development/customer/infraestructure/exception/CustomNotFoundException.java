package com.development.customer.infraestructure.exception;

import org.springframework.http.HttpStatus;

public class CustomNotFoundException extends CustomException {
    public CustomNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
