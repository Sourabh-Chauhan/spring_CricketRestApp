#FROM openjdk:8-jdk-alpine
FROM eclipse-temurin:17-jdk-focal
#FROM openjdk:8
EXPOSE 8080
ADD target/spring-boot-docker.jar spring-boot-docker.jar
ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]