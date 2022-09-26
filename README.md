# User management system (UMS)

### Project description
The application is needed for controlling the list of users.
It has two parts: backend and fronted.
User can work via an Internet browser with the frontend single page application (SPA) 
and in that time the SPA application communicates with the backend part.

Pages on the frontend part:
   > /login - log in user

   > /user - show list of user

   > /user/view - view information about user

   > /user/add - add user

   > /user/edit/:id - edit user with id

Main endpoints on the backend part:
   > GET /user - get list of users

   > GET /user/{id} - get info about the user with id

   > POST /user - create new user

   > PUT /user/{id} - update info about the user with id

### Features
- The frontend part works on Angular JavaScript framework
- The backend part works on Spring Boot Java framework

Users have roles: ADMIN or USER. User after authentication gets some role 
and depends on it can do some operation: VIEW/ADD/EDIT/LOCK user in the application.

### Project architecture
The backend part of the project:
1. Controllers - Presentation layer
2. Services - Application layer
3. DAO - Data access layer

The frontend part of the project:
1. UsersComponent - show list of users
2. UserViewComponent - show information about the user
3. UserEditComponent - add/edit information about the user

There a lot of services for working with data.

### Technologies used in project
- Spring Boot 2.7, Spring Web, Spring Data Jpa, Spring Security
- MySQL v.8
- Java v.11
- Angular v.14
- Apache Maven v.3.8
- Docker, Docker compose

### For launch project

1. You should have ready to use docker, docker-compose and git programs.

2. Clone this project. Run in terminal:
   > git clone https://github.com/gdpbalt/user-management-system.git

3. Copy file `env.example` to `.env` in the root directory of the project and edit it.  

4. Build and run project via docker-compose. 
Go to the project directory and run in terminal: 
   > docker-compose up

5. Open in your browser on http://localhost:4200

6. Enter username and password in login form:
   - user/test123 for user with role USER;
   - admin/test123 for user with role ADMIN.
