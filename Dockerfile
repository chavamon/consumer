# Use Maven image to build the project
# FROM maven:3.8.6-openjdk-21 AS  build
FROM maven:3.9-amazoncorretto-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use OpenJDK 21 image to run the application
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage to the run stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application will run on
# EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
