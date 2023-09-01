# define the Shebang
#!/bin/bash

# call the docker commands to run the mongo databases
docker run -d -p 27017:27017 --name=property-mongo mongo:4.4
docker run -d -p 27018:27017 --name=edge-mongo mongo:4.4
docker run -d -p 27019:27017 --name=property-data-mongo mongo:4.4
docker run -d -p 27020:27017 --name=user-data-mongo mongo:4.4

# verifying if starting the docker images were successful
if [ $? -eq 0 ]; then
  echo "Starting mongo docker images successful."
else
  echo "Starting mongo docker images failed."
fi