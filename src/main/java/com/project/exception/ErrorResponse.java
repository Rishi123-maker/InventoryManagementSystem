package com.project.exception;
 
import java.time.LocalDateTime;
import lombok.*;
 
@Data
@Getter
@Setter
public class ErrorResponse {
	private String msg;
	private String details;
	private LocalDateTime time;
 
	public ErrorResponse(LocalDateTime time, String msg, String details) {
		this.msg = msg;
		this.details = details;
		this.time = time;
	}
}