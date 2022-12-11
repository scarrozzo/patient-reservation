# Patient Reservation

This is a teaching project that aims to show an example of a web application developed with the following technologies: REST API (Spring Boot), single page application (Angular), relational databases, JWT token authentication, OpenAPI, database change automation (Liquibase), application containerization (docker/docker-compose).

## Functional Requirements
Developing a running web application. 
This application will have to manage basic patient data (name, surname, date of birth and social security number). For each patient should be possible to record one or more visit(s). Each visit has to contain the date and time of the visit, the tupe od the visit (either at home or at the doctor office), the reason of the visit (first visit, recurring visit, urgent) and the family history (a free text section).
The application workflow has to:
- select a patient
- create a visit for the patient
- view/update a visit
- all data have to be persisted


## Project Description
The project consists of the following sub-projects:

**core-lib**: Maven library containing abstract base classes and utility classes that can be reused in other projects.<br /> 
**reservation**: spring boot application (version 3.0.0) that handles data persistence and implementation of the REST API used by the frontend.<br /> 
**frontend**: single page application implemented with Angular (version 14.2.0). <br /> 

### Reservation Project
Features implemented in this project:
- Containerization of API and MySQL DBMS exploiting docker files and docker-compose. To run the backend application within docker, it is sufficient to execute the following steps:
	1) Maven clean install of the library: `mvn clean install` (inside the core-lib folder).
	2) Maven clean install of the API project: `mvn clean install` (inside the reservation folder).
	3) Run the docker-compose command inside the docker folder of the API project: `docker-compose -f docker-compose.yml -p patient-reservation up --build -d`
- Swagger documentation automatically generated and enriched via open api annotations on controllers. Once you import the "reservation" project within your IDE and launch the application with the Spring "local" profile you can see the API documentation at the url: http://localhost:8080/swagger-ui/index.html
- Automatic execution of sql DDL scripts using the Liquibase library. At startup of the Spring "reservation" application, the data model is automatically created via connection to the MySQL database (remember to start the MySQL container described in step one). Liquibase also makes it possible to keep track of all changes that have occurred on database and to be able to perform new changelogs if new API versions require it via new sql/xml scripts.
- Auditing and optimistic locking on the data model: all created entities contain audit fields automatically saved by the framework (creation date, last modification date, creation user, last modification user). In addition, the framework has been configured to automatically handle optimistic locking via the "version_" field on the tables.
- Multiple endpoints covering all functional requirements: 
	- Login: username/password authentication endpoint for generating a JWT token via private key. 
	- API for user management: create user, user list, user detail, delete user.
	- API for patient management: create patient, update patient, search patient, patient detail, delete patient.
	- API for visit management: create visit, update visit, search visit, visit detail, delete visit.
- The API was implemented using the HATEOAS paradigm by exploiting assembler and representation model.
- Spring security: filter for JWT token validation. Basic auth on management paths (swaggers, actuators). There are also roles on databases 
associated with different user types (admin, doctors, patients). The roles are controlled during API access through the Spring PreAuthorize annotations on controllers.
- Error handling: all exceptions are handled by the Spring controller advice component, which is responsible for returning code and a message to the client. The code can be used by clients to map messages exploit i18n
- Unit tests

### Frontend Project
The UI unlike the backend does not cover all the functional requirements at the moment.<br />
Features implemented in this project:
- Login page/component: invokes the login endpoint and if successful redirects to the visit calendar page
- Visit calendar/component: invokes the visit search API and populates the calendar with visits associated with the logged-in user (doctor)
- JWT token management: save/delete token at login/logout, automatic token forwarding via http interceptor.


## Getting started

### Backend
1) Clone/download repository
2) Import the three projects within your favorite IDE. If you use IntelliJ Idea, it is possible to import the three projects into the same window as modules: import the "reservation" project first as a Maven project, then import core-lib as a Maven module (right-click on the "reservation" project, then on "Open module settings" and finally click on the "+" button), finally import the "frontend" project as a generic module (similar procedure as described for core-lib).
3) Maven clean install of the library: `mvn clean install` (inside the core-lib folder).
4) Maven clean install of the API project: `mvn clean install` (inside the reservation folder).
5) Run the docker-compose command inside the docker folder of the API project: `docker-compose -f docker-compose.yml -p patient-reservation up --build -d`
6) You can invoke the API executed inside the docker container (port 8081) or run the "reservation" application as a Java application inside the IDE by passing the "profile" argument (-Dspring.profiles.active=local). In the latter case, the API will be reachable on port 8080.
7) To check invocable APIs you can use Swagger reachable at: http://localhost:8080/swagger-ui/index.html

### Frontend
1) Install angular cli from the command line (install node/npm on your computer if not already available): `npm install -g @angular/cli@14.2.10`
2) Move from the command line inside the frontend folder and run the command: `npm install`
3) Start the application from the command line within the frontend folder: `ng serve`
4) Open the browser at the following address: http://localhost:4200/
5) From the login page visible on the browser page use the doctor's credentials:  username: drhouse / password: password123?
6) Scheduled visits will be visible within a calendar
