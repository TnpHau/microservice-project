#Dockerfile eureka-server
FROM openjdk:20-jdk
EXPOSE 12003
COPY /target/eureka-server.jar /eureka-server.jar
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]

