# Define the Shebang
#!/bin/bash


# Call the docker stop command to delete all running containers
docker stop $(docker ps -q)


# Verify if stopping all the running containers were successful
if [ $? -eq 0 ]; then
  echo "Stopping all docker containers completed successfully."
else
  echo "Stopping all docker containers failed."
fi
