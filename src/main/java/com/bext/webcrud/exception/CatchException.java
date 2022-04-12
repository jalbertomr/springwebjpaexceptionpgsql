package com.bext.webcrud.exception;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class CatchException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	
	public CatchException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

}
