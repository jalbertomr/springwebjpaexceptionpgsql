## Spring Web jpa Person with exceptions, profiles for H2, Postgresql, initialization of tables by properties data.sql

Application of person with operations CRUD using JPARepository with Global Exception handling
Initialization of tables by application.resources and profiles

## Profiles Used

  h2Initdata
  pgsqlInitdata
  
###  Using profile h2InitData then sql files used to init data
  
  application.properties
  application-h2InitData.properties
  data.sql
  data-h2.sql
  schema-h2.sql
  
  In properties file is configured spring.sql.init.platform=h2
  
###  Using profile pgsqlInitdata then sql files used to init data
  
  application.properties
  application-pgsqlInitdata.properties
  data.sql
  data-postgres.sql
  schema-postgres.sql
  
  In properties file is configured spring.sql.init.platform=postgres

#### Exceptions

With use of a Global Exception Hangler, the many try-catch on many levels like business level, service level...
can be eliminated and managed in only one place, a class with annotation @ControllerAdvice managing the exceptions 
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


#### Create / init Database

    spring.sql.init.mode = never             // does not execute data.sql,schema.sql files 
    spring.jpa.hibernate.ddl-auto = none    // does not create database automatically

    spring.sql.init.mode = always            // does not execute data.sql,schema.sql files 
    spring.jpa.hibernate.ddl-auto = none    // does not create database automatically

The sql scripts for tables include the sequence for the primary keys

#### Validation of Fields using javax.validation and handling his Exceptions

To manage the validation on fields with javax.validation use annotations @NotBlank @NotNull @Min() @Max() ...
message can be customized for the respective validation

    @Entity
    @Table(name="person_jpa")
    @Data
    public class Person {
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   private Long id;
	
	   @Column(nullable = false)
	   @NotBlank @NotNull(message = "firstName cannot be null") 
	   private String firstName;
	
	   @Column(nullable = false)
	   @NotNull
	   private String lastName;
	
	   @Min(1) @Max(200) 
	   private int age;
	
	   private Integer integer;
    }
    
  To handle this validations add in GlobalControllerHandler which has @ControllerAdvice annotation to manage globally
  add the method to manage the validation at controller level that uses the @Valid annotation on Controller Method.  
  
     	// error to handle @valid
	   @Override
	   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		LinkedHashMap<Object, Object> body = new LinkedHashMap<>();
		body.put("timestamp", "");
		body.put("status", status.value());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(x -> x.getField() + ": " + x.getDefaultMessage())
				.map(x -> x + ", " + new Date())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>( errors, headers, status);
	  }
	  
 In Controller Method
 
    @PostMapping
	 public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person){
		Person newPerson = iPersonService.addPerson( person);
		...
    
#### Sequence of primary key especified

  The name of the sequence for the primary key (when simple) is specified on @Entity table
        
    @Entity
    @Table(name="personjpa")
    @Data
    public class Person {
     
    @Id
	 @GeneratedValue(strategy = GenerationType.AUTO, generator="PERSONJPA_SEQ")
	 private Long id;
  