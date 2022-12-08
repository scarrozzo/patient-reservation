FROM openjdk:17-oracle
MAINTAINER com.patient.reservation
COPY reservation-0.0.1-SNAPSHOT.jar /usr/local/lib/reservation-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/reservation-0.0.1-SNAPSHOT.jar"]
CMD ["--spring.profiles.active=local"]