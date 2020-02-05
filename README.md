# phorest-techtest-romansegeda

 ## Info
 
 Application exposes Restful API for a booking service.
 New client can import the existing data of appointments and clients into the system using this API.
 
 ## Tech stack
 
 Using:
 1. Gradle Wrapper 6.0.1
 2. JDK 13
 3. Spring Boot 2.2.4 RELEASE
 4. Spring Boot Actuator
 5. Spring Boot Dev Tools
 6. Spring Boot REST Docs
 7. Swagger + Swagger UI + Swagger validator
 8. Lombok
 9. Docker + Docker Compose
 10. JUnit Jupiter + JaCoCo
 

 ## How to:
 
 ### - configure
 By default, the instance of MongoDB server will be instantiated using the [docker-compose.yml
 ](docker-compose.yml]) config file. To change the the mongodb URI edit the **booking-service
 -core.environment.CONFIG_MONGO_URI** property.
   
 ### - run
  In order to start the service (requires docker-compose), execute:
    
    ./gradlew installBootDist composeUp
    
  To shutdown the service - call 
    
    ./gradlew composeDown
    
 ## How to use API
 
 ### Swagger UI
 
 Available at http://localhost:8080/swagger-ui.html
 ### Example requests collection
 > Generated from the integration tests with the help of Spring REST Docs

Example requests (snippets) are available after running 

`./gradlew build`

under the [build/generated-snippets](build/generated-snippets)
 
 ### Spring actuator info
 
 To check if the app is running, query
 [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
 
 ## Test coverage
 
 to generate test coverage report:
 `./gradlew test jacocoTestReport`
 available under the following path [build/reports/jacoco/index.html](build/reports/jacoco/index.html)
 
 ## Integration tests
 
 to run integration tests:
 * run `./gradlew integrationTest`