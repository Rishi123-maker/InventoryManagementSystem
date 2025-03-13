package com.project.exception;

public class IdNotFoundException extends RuntimeException{
	//Create constructor
	public IdNotFoundException(String message) {
		super(message);
	}
}
