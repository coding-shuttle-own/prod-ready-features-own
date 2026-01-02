package com.anee.module4_prod_ready_features_own.prod_ready_features_own.advice;

import com.anee.module4_prod_ready_features_own.prod_ready_features_own.exceptions.ResourceNoteFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNoteFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNoteFoundException ex) {
        ApiError apiError = new ApiError(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
