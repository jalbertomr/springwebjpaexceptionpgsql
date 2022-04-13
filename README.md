## Spring Web jpa Person with exceptions

Application of person with operations CRUD using JPARepository with Global Exception handling

#### Exceptions

With use of a Global Exception Hangler, the many try-catch on many levels like business level, service level...
can be eliminated and managed in only one place, a class with annotation @ControllerAdvice managind the exceptions 
in an global way, returning a controlled ResponseEntity to the client.

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
	public ResponseEntity<String> hadleNoSuchElement(NoSuchElementException nsee){
		return new ResponseEntity<String>("Error - " + nsee.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PropertyValueException.class)
	public ResponseEntity<String> hadlePropertyValue(PropertyValueException pve){
		return new ResponseEntity<String>("Error - " + pve.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
}


####