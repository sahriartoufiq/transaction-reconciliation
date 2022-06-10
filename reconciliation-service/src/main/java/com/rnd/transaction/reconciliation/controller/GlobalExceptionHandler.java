package com.rnd.transaction.reconciliation.controller;

import com.rnd.transaction.reconciliation.response.ErrorResponse;
import com.rnd.transaction.reconciliation.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public final ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
        var errorResponse = getErrorResponse(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MultipartException.class)
    public final ResponseEntity<ErrorResponse> handleMultipartException(MultipartException ex) {
        var errorResponse = getErrorResponse("Invalid request!");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleException(Exception ex) {
        var errorResponse = getErrorResponse(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse getErrorResponse(String errorMessage) {
        return ErrorResponse.builder()
                .isError(true)
                .errorMessage(errorMessage != null ? errorMessage : "An error occurred!")
                .build();
    }
}
