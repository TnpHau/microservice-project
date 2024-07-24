#Dockerfile product-service
FROM openjdk:20-jdk
EXPOSE 12002
COPY /target/product-service.jar /product-service.jar
ENTRYPOINT ["java", "-jar", "/product-service.jar"]

