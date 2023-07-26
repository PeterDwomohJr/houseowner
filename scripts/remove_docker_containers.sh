# Define the Shebang
#!/bin/bash


# Call the docker rm command to remove all running containers
docker stop $(docker ps -q)


# Verify if removing all the running containers were successful
if [ $? -eq 0 ]; then
  echo "Removing all docker containers completed successfully."
else
  echo "Removing all docker containers failed."
fi
