#Define the Shebang
#!/bin/bash

# Set the path of the Edge directory variable
PROJECT_DIRECTORY="/home/kwadwo/houseowner/edge"

# Change to the project directory
cd $PROJECT_DIRECTORY


# Build the Spring Boot app with Maven and skip tests
mvn clean package -DskipTests

# Verify if the build was successful
if [ $? -eq 0 ]; then
  echo "Building Property jar file completed successfully."
else
  echo "Building Property jar file failed."
fi
