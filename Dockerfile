# Use the official OpenJDK image for Java 21
FROM openjdk:21

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven pom.xml and source code into the container
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests

# Copy the built jar file from the target directory
COPY target/*.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]