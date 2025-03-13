package com.project.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.entity.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> handleIdNotFoundException(IdNotFoundException ex){
		ErrorResponse response = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), "Id not found");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
		ErrorResponse response = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), "Resource not found");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
