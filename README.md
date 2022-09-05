# User management system (THIS IS NOT COMPLETE DOCUMENT)

### Project description
Application work with the base of users.
Have two parts: backend and fronted.
User can work via an Internet browser with the frontend application
(SPA application).
In that time SPA application communicates with the backend part.

Pages on the frontend part:
> /user - show list of user

> /user/add - add user

> /user/edit/:id - edit the user with id

Main endpoints on the backend part:
> GET /user - get list of users

> GET /user/{id} - get info about the user with id

> POST /user - create new user

> PUT /user/{id} - update info about the user with id

### Features
- Frontend part works on Angular JavaScript framework
- Backend part works on SpringBoot Java framework

### Project architecture
Backend part of the project:
1. Controllers - Presentation layer
2. Services - Application layer
3. DAO - Data access layer

Frontend part of the project:
1. UsersComponent - show list of users
2. UserEditComponent - add/edit information about the user

### Technologies used in project
- Spring Boot 2.7, Spring Web, Spring Jpa
- MySQL
- Java v.17
- Apache Maven v.3.8
- Docker, Docker compose
- Angular v.14

### For launch project

1. Install Docker Desktop and register on DockerHub

2. Run next command in terminal from `user-management-system-api` directory:

   > mvn clean package

[//]: # (3. Run next command in terminal from the main directory:)
[//]: # ()
[//]: # (   > docker-compose up --build)
[//]: # ()
[//]: # (4. Open your browser on http://localhost:6868.)
