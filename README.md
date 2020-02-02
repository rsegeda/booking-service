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
 7. Lombok
 8. Docker + Docker Compose
 9. JUnit Jupiter + JaCoCo
 

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
 
 ### TODO postman collection
 > todo.json contains query templates
>
 ### TODO Spring REST docs
 
 
 ### TODO Spring actuator info
 
 ## Test coverage
 
 to generate test coverage report:
 `./gradlew test jacocoTestReport`
 available under the following path [build/reports/jacoco/index.html](build/reports/jacoco/index.html)
 
 ## Integration tests
 
 to run integration tests:
 * run `./gradlew integrationTest`
 `NOTE: This step requires docker. Integration tests are preceeded by running docker compose up
  automatically. Two services would be created. After the tests are done, the compose down is
   executed.`
 