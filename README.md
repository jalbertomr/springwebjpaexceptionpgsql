## Spring Web jpa Person with exceptions on Postgresql

Application of person with operations CRUD using JPARepository with exception handling

#### Exceptions

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


