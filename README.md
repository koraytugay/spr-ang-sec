# Spring Boot - Angular Security Sample Application

## Quick Start
### Server
- Build with `mvn install`
- Run `SprAngSecSrv.java`

### Client
- Build with `npm install`
- Run with `ng serve`

### Hello World
- Navigate to `localhost:4200`
  - Validate you cannot navigate to either `foo` or `bar`
  - You will need to login, use credentials `john:doe`
  - You can alternatively use `a:a`
  - Validate you can navigate to either `foo` or `bar`

## What This Is
This is a sample Angular - Spring Boot application with a focus on authentication. 
There are many improvements that can be done. It can be used to study / investigate how
basic authentication (passing `JESSIONCOOKIE` between the client and the server) and how JWTs can be used.
See branch `http-basic` for how Basic authentication can be implemented. See branch `jwt` for the
first iteration of the `jwt` implementation, which stores the JWT token in-memory in front-end. 
See the second iteration in branch `jwt-with-cookie`. 

Reference for this work is [Angular Security and Deployment](https://www.manning.com/livevideo/angular-for-java-developers-security-and-deployment) 
and more explanation can be found at [this](https://koraytugay.github.io/content/spring-angular-authentication.html) page.
