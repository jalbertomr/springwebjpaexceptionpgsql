package com.bext.webcrud.advice;

import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bext.webcrud.controller.PersonController;
import com.bext.webcrud.exception.CatchException;
import com.bext.webcrud.exception.EmptyInputException;
import com.bext.webcrud.repo.PersonJpaRepository;
import com.bext.webcrud.service.PersonServiceImpl;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
public class GlobalControllerHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>("Check the Request Method/Path", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException eie) {
		return new ResponseEntity<String>("Error - " + eie.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException iae){
		return new ResponseEntity<String>("Error - " + iae.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElement(NoSuchElementException nsee){
		return new ResponseEntity<String>("Error - " + nsee.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PropertyValueException.class)
	public ResponseEntity<String> handlePropertyValue(PropertyValueException pve){
		return new ResponseEntity<String>("Error - " + pve.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityExistsException.class)
	public ResponseEntity<String> handleEntityExist(EntityExistsException eae) {
		return new ResponseEntity<String>("Error - " + eae.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlePSQL(Exception e){
		return new ResponseEntity<String>("Error - " + e.getLocalizedMessage() + e.getCause(), HttpStatus.METHOD_FAILURE );
	}
	
}
