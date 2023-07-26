# Define the Shebang
#!/bin/bash


# Define the path of the Pulsar docker-compose.yml resource
PULSAR_DOCKER_COMPOSE_PATH="/home/kwadwo/houseowner/docker/pulsar"


# Change to the Pulsar docker-compose.yml rresource
cd $PULSAR_DOCKER_COMPOSE_PATH


# Define the Pulsar docker-compose.yml
PULSAR_DOCKER_COMPOSE="docker-compose-pulsar.yml"


# Apply the Pulsar docker-compose.yml resource
docker-compose -f $PULSAR_DOCKER_COMPOSE up -d



# Verify if starting the Pulsar container was successful
if [ $? -eq 0 ]; then
  echo "Starting Pulsar container completed successfully."
else
  echo "Starting Pulsar container failed."
fi