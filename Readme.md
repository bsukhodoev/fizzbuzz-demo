# Fizz Buzz Demo Application

This application implements [Fizz Buzz](https://en.wikipedia.org/wiki/Fizz_buzz) game via REST API. REST API documentation accessible on the /swagger-ui.html page.

## How to run

Application build on Spring Boot 2 and can be started locally by the following command (on Linux or Mac):
  
    ./mvnw spring-boot:run
  
Web page to play this game should be here: [http://localhost:8080/](http://localhost:8080/)

Go to the [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to find out REST API documentation and try example.

## How to build

Execute the following command to create executable JAR:

    ./mvnw package

Executable JAR (fizzbuzz-demo-x.x.x.jar) can be found in the "target" folder.
NOTE: executable JAR may not work properly on some systems.

## License

This application built for demo purposes only.
