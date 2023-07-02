#Define the Shebang
#!/bin/bash

# Set the path of the Edge jar directory variable
PROJECT_DIRECTORY="/home/kwadwo/houseowner/edge/target"

# Change to the project directory
cd $PROJECT_DIRECTORY

# Set the name of the jar file
PROPERTY_JAR_FILE="edge-0.0.0-SNAPSHOT-jar"


# Delete the property-jar file
rm $PROJECT_JAR_FILE


# Verify if removing the jar file was successful
if [ $? -eq 0 ]; then
  echo "Jar file removal completed successfully."
else
  echo "Jar file removal failed."
fi
