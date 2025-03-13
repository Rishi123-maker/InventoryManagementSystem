package com.project.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ErrorResponse {
		
		private LocalDateTime timestamp;
	    private String message;
	    private String details;

	    public ErrorResponse(LocalDateTime timestamp, String message, String details) {
	    	super();
	        this.timestamp = timestamp;
	        this.message = message;
	        this.details = details;
	    }
}
