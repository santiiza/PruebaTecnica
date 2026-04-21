package com.development.customer.infraestructure.exception;

import com.development.customer.domain.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<ErrorResponseDto> handleServerPSQLException(PSQLException ex) {
        log.error("<-| Process PSQLException finished with error - message: {}", ex.getMessage(), ex);
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error en la base de datos: " + ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomException(CustomException ex) {
      log.error("<-| Process CustomException finished with error - message:{}", ex.getMessage(), ex);
      ErrorResponseDto errorResponse = new ErrorResponseDto(
              ex.getHttpStatus().value(),
              "Error: " + ex.getMessage(),
              LocalDateTime.now()
        );
      return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
