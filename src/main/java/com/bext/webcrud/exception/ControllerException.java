package com.bext.webcrud.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ControllerException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private String errorCode;
	private String errorMessage;
}
