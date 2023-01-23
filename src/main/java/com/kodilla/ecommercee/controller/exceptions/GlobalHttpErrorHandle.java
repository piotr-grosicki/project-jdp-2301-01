package com.kodilla.ecommercee.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleNotFoundException(ProductNotFoundException exception){
        return new ResponseEntity<>("Requested data doesn't exist", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<Object> handleNotFoundException(GroupNotFoundException exception){
        return new ResponseEntity<>("Requested data doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
