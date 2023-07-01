#!/bin/bash

# Change to the project directory
cd /home/kwadwo/houseowner/property


# Build the Spring Boot app with Maven and skip tests
mvn clean package -DskipTests

# Verify if the build was successful
if [ $? -eq 0 ]; then
  echo "Build completed successfully."
else
  echo "Build failed."
fi
