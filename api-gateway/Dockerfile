#Dockerfile api-gateway
FROM openjdk:20-jdk
EXPOSE 6789
COPY /target/api-gateway.jar /api-gateway.jar
ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]

