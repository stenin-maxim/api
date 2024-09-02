FROM openjdk:21-jdk as build
WORKDIR /api
COPY target/*.jar api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","api-0.0.1-SNAPSHOT.jar"]