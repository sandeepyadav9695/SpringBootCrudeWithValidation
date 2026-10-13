package com.inwithsky.CrudeSpringBootDemo.exception;

import com.inwithsky.CrudeSpringBootDemo.dto.ExceptionDto;
import com.inwithsky.CrudeSpringBootDemo.dto.ValidExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Resource Not Found - 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleResourceException(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        ExceptionDto exceptionDtoRes = new ExceptionDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionDtoRes);
    }

    // 2. Duplicate Resource - 409
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionDto> handleDuplicateResourceException(
            DuplicateResourceException ex,
            HttpServletRequest request) {

        ExceptionDto exceptionDtoRes = new ExceptionDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionDtoRes);
    }

    // 3. Validation Error - 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidExceptionResponseDto>
    handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> fieldError = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        fieldError.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        ValidExceptionResponseDto exceptionRes =
                new ValidExceptionResponseDto(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        "Validation failed",
                        request.getRequestURI(),
                        fieldError
                );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionRes);
    }

    // 4. Runtime Exception - 500
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntimeException(
            RuntimeException ex,
            HttpServletRequest request) {

        ExceptionDto exceptionDtoRes = new ExceptionDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionDtoRes);
    }

    // 5. Generic Exception - 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        ExceptionDto exceptionDtoRes = new ExceptionDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionDtoRes);
    }
}