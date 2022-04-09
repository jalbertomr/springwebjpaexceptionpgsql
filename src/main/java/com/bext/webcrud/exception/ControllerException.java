package com.bext.webcrud.exception;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class ControllerException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private String errorCode;
	private String errorMessage;
	
	public ControllerException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = "Controller Level-" + errorMessage;
	}
	
	
}
