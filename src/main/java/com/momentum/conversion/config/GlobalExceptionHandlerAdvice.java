package com.momentum.conversion.config;

import com.momentum.conversion.Model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception while processing : {}, exception type :",e.getMessage(), e);
        String message = e.getMessage()!=null?e.getMessage():"Something went wrong. Please connect with Support team";
        ErrorResponse response = new ErrorResponse(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
