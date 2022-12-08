# patient-reservation

### Start database and APIs in Docker:
1) mvn clean package
2) docker-compose -f docker-compose.yml -p patient-reservation up --build -d

### Start in local env:
1) start mysql container
2) mvn spring-boot:run

OR

1) start mysql container
2Import projects into IntelliJ IDEA and run "patient-reservation" as a Java application

### API DOC
1) Start "patient-reservation" application
2) Go with the browser to the address: http://localhost:8080/swagger-ui