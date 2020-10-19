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

## What This Is
This is a sample Angular - Spring Boot application with a focus on authentication. 
There are many improvements that can be done. The purpose of this project is to show how base64 
encoded basic authentication headers can be passed from angular to backend, and how the JSESSIONID
can consequently be used. 

When user sends username and password in login form in Angular, they are sent via HTTP Basic 
Authentication to Spring Security. If authentication is successful, consequent requests include the
JSESSIONID, from which Spring Security can fetch the user.