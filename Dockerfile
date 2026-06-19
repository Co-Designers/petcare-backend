# Dockerfile for petcare-platform
# Builds and runs the PetCare Platform Spring Boot REST API.

# Step 1: Build the application using Maven and Eclipse Temurin JDK 21
FROM maven:3.9.16-eclipse-temurin-21 AS build

WORKDIR /app

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

# Step 2: Create the runtime image
FROM eclipse-temurin:21-jre AS runtime

ENV SPRING_PROFILES_ACTIVE=prod

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
