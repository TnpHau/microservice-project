#Dockerfile api-gateway
FROM openjdk:20-jdk
EXPOSE 12001
COPY /target/user-service.jar /user-service.jar
ENTRYPOINT ["java", "-jar", "/user-service.jar"]

