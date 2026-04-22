package com.development.account.infraestructure.exception;

import com.development.account.domain.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<ErrorResponseDto> handleServerPSQLException(PSQLException ex) {
        log.error("<-| Process PSQLException finished with error - message: {}", ex.getMessage());
        log.debug("<-| Process PSQLException finished with error - message: {}", ex.getMessage(), ex);
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
      log.error("<-| Process CustomException finished with error - message:{}", ex.getMessage());
      log.debug("<-| Process CustomException finished with error - message:{}", ex.getMessage(), ex);
      ErrorResponseDto errorResponse = new ErrorResponseDto(
              ex.getHttpStatus().value(),
              "Error: " + ex.getMessage(),
              LocalDateTime.now()
        );
      return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("<-| Process HttpRequestMethodNotSupportedException finished with error - message:{}", ex.getMessage());
        log.debug("<-| Process HttpRequestMethodNotSupportedException finished with error - message:{}", ex.getMessage(), ex);
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                "Error: " + ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("<-| Process MethodArgumentNotValidException finished with error - message:{}", ex.getMessage());
        log.debug("<-| Process MethodArgumentNotValidException finished with error - message:{}", ex.getMessage(), ex);
        Map<String, String> fieldErrors = processErrors(ex.getBindingResult().getFieldErrors());
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                "Error: " + fieldErrors,
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("<-| Process HttpMessageNotReadableException finished with error - message:{}", ex.getMessage());
        log.debug("<-| Process HttpMessageNotReadableException finished with error - message:{}", ex.getMessage(), ex);
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                "Error: " + ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ErrorResponseDto> handleUnknownHostException(UnknownHostException ex) {
        log.error("<-| Process UnknownHostException finished with error - message:{}", ex.getMessage());
        log.debug("<-| Process UnknownHostException finished with error - message:{}", ex.getMessage(), ex);
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error: " + ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("<-| Process IllegalArgumentException finished with error - message:{}", ex.getMessage());
        log.debug("<-| Process IllegalArgumentException finished with error - message:{}", ex.getMessage(), ex);
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                HttpStatus.CONFLICT.value(),
                "Error: " + ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    private Map<String, String> processErrors(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (msg1, msg2) -> msg1 + ", " + msg2
                ));
    }

}
