FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/capitole-product-api-0.0.2-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
