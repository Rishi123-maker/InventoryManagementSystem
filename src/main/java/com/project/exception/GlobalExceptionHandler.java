
package com.project.exception;

import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(IdNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>("An error occurred: " +ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex,WebRequest request)
    {
    	return new ResponseEntity<>("An error occurred: "+ex.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InappropriateDateException.class)
    public ResponseEntity<String> inappropriateDateException(InappropriateDateException ex,WebRequest request)
    {
    	return new ResponseEntity<>("an error occurred: "+ex.getMessage(),HttpStatus.NOT_FOUND);
    }
    

}
