package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = TrackAlreadyExistsException.class)
    public ResponseEntity<String> exceptionHandler(TrackAlreadyExistsException e) {
        return new ResponseEntity<>("exception is present" + e.getMessage(), HttpStatus.CONFLICT);
    }
}
