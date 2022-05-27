## [Spring Web jpa Person with Global Exception Handler, Profiles for H2, Postgresql. table init by properties data.sql](https://github.com/jalbertomr/springwebjpaexceptionpgsql/tree/GlobalExceptionsHandling).

## Spring Web jpa Person with exceptions on Postgresql

Application of person with operations CRUD using JPARepository with exception handling

#### Exceptions

Using Custom Exception handling
Exceptions at Controller and Business level

Business Level
  BusinessException
  Catch errors of Repository and add to the errormessage field a prefix of Business Level-
  Repository.findById( id) generate org.springframework.dao.InvalidDataAccessApiUsageException
  when not find and is catched.
  
  PersonService
  Validate fields create BusinessException and throws it with respective errorcode and errormessage.
  
Controller Level  
  ControllerException
  Catch errors of Repository and add to the errormessage field a prefix of Controller Level-
  
  PersonController
  validate fields create ControllerException to just be attached to the ResponseEntity<ControllerException>(HttpStatus.BAD_REQUEST)

  Person entity
    field property @Column(nullable = false) on lastName is covered.
    
  To Validate the body on the request use a exceptionhandler in the controller with annotation 
  
    	@ExceptionHandler(HttpMessageNotReadableException.class)
	     public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException hmnre) {
		  return new ResponseEntity("Incorrect Body in request", HttpStatus.BAD_REQUEST);
	   }
 
 This way many try-catch validations are present to minimize the code use @ControllerAdvice as used on this repo branch 
 [GlobalExceptionHandling](https://github.com/jalbertomr/springwebjpaexceptionpgsql/tree/GlobalExceptionsHandling).
 also @ControllerAdvice can share the variable buffer between Controllers for @initBind, @ModelAttribute
    
 To catch a Exception
 
    @ExceptionHandler(Exception.class)
	 public ResponseEntity<String> handlePSQL(Exception e){
      return new ResponseEntity<String>("Error - " + e.getLocalizedMessage() 
      + e.getCause  (),HttpStatus.METHOD_FAILURE );
    }   
    
#### Person Entity

Long   id

String firstName

String lastName

int    age


#### Operations on JpaRepository

findById

findByFirstName

findByLastName

findByAge


