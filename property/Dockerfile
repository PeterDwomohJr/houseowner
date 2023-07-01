# Use a base image with Java 17
FROM openjdk:17-jdk

# Set the working directory in the container
WORKDIR /app
  
# Copy the JAR file into the container
COPY target/property-0.0.0-SNAPSHOT.jar /app/property-0.0.0-SNAPSHOT.jar
  
# Set the command to run the Spring Boot application
CMD ["java", "-jar", "/app/property-0.0.0-SNAPSHOT.jar"]
